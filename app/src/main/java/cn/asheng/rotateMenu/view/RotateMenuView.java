package cn.asheng.rotateMenu.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.List;

import cn.asheng.rotateMenu.R;
import cn.asheng.rotateMenu.utils.ViewUtils;


/**
 * Created by Administrator on 2016/10/25.
 */
public class RotateMenuView extends RelativeLayout implements View.OnClickListener {
    private static final int DEFULT_HORIZONTAL_MARGIN = 10;//行间距
    private static final int DEFULT_VERTICAL_MARGIN = 50;//列间距
    private static final int DEFULT_CENTER_ROTATE = 225;//中间按钮旋转角度
    private static final int DEFULT_ITEM_ROTATE = 360;//item旋转角度
    private static final int DEFULT_BACKGROUD_COLR = Color.WHITE;//背景颜色
    private static final int DEFULT_CENTER_ADD_SRC = R.mipmap.add_publish_fragment_bg;//默认加号

    private View backgroudView;
    private ImageView mIvCenterAdd;
    private boolean isShowMenu = false;
    private Context mContext;

    private OnRotateItemClickListner onRotateItemClickListner;
    private OnCenterAddClickAdd onCenterAddClickAdd;
    private TableLayout viewCreateAll;
    private List<RotateMenuItem> rotateMenuItemList = new ArrayList<>();
    private float horizontalMargin;
    private float verticalMargin;
    private int centerRotate;
    private int itemRotate;
    private int backGoundColor;

    public void setOnCenterAddClickAdd(OnCenterAddClickAdd onCenterAddClickAdd) {
        this.onCenterAddClickAdd = onCenterAddClickAdd;
    }

    public void setOnRotateItemClickListner(OnRotateItemClickListner onRotateItemClickListner) {
        this.onRotateItemClickListner = onRotateItemClickListner;
    }

    public boolean isShowMenu() {
        return isShowMenu;
    }

    public void setShowMenu(boolean showMenu) {
        isShowMenu = showMenu;
    }

    public RotateMenuView(Context context) {
        this(context, null, 0);
    }

    public RotateMenuView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RotateMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        LayoutInflater.from(mContext).inflate(R.layout.view_rotate_menu, this, true);
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.RotateMenuView);
        horizontalMargin = typedArray.getDimension(R.styleable.RotateMenuView_horizontal_margin, ViewUtils.dip2px(mContext, DEFULT_HORIZONTAL_MARGIN));
        verticalMargin = typedArray.getDimension(R.styleable.RotateMenuView_vertical_margin, ViewUtils.dip2px(mContext, DEFULT_VERTICAL_MARGIN));
        centerRotate = typedArray.getInt(R.styleable.RotateMenuView_center_rotate, DEFULT_CENTER_ROTATE);
        itemRotate = typedArray.getInt(R.styleable.RotateMenuView_item_rotate, DEFULT_ITEM_ROTATE);
        backGoundColor = typedArray.getColor(R.styleable.RotateMenuView_back_ground_color, DEFULT_BACKGROUD_COLR);
        int addSrc = typedArray.getResourceId(R.styleable.RotateMenuView_center_add_src, DEFULT_CENTER_ADD_SRC);
        typedArray.recycle();

        viewCreateAll = (TableLayout) findViewById(R.id.viewCreateAll);
        backgroudView = findViewById(R.id.backgroud_view);
        backgroudView.setOnClickListener(this);
        mIvCenterAdd = (ImageView) findViewById(R.id.btnCreateMenu);
        mIvCenterAdd.setImageResource(addSrc);
        mIvCenterAdd.setOnClickListener(this);
        backgroudView.setBackgroundColor(backGoundColor);
    }

    public void setCreateItems(List<RotateMenuItem> list) {
        if (list != null && list.size() > 0) {
            rotateMenuItemList = list;
            TableRow tablerow = null;
            for (int i = 0; i < rotateMenuItemList.size(); i++) {
                //能被二整除 或者是第一次到循环中
                if (tablerow == null || i % 2 == 0) {
                    tablerow = new TableRow(mContext);
                    tablerow.setGravity(Gravity.CENTER);
                }
                tablerow.addView(rotateMenuItemList.get(i));
                //当tablerow child为2，或者i为最后一个时才添加（防止总个数为奇数情况）
                if (i == rotateMenuItemList.size() - 1 || tablerow.getChildCount() == 2) {
                    viewCreateAll.addView(tablerow);
                }
                //设置监听
                rotateMenuItemList.get(i).setOnClickListener(this);
                rotateMenuItemList.get(i).setTag(i);
                //设置旋转角度
                rotateMenuItemList.get(i).setItemRotate(itemRotate);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backgroud_view:
                hideCreateMenu();
                break;
            case R.id.btnCreateMenu:
                if (onCenterAddClickAdd != null) {
                    onCenterAddClickAdd.onclickAdd();
                    return;
                }
                if (isShowMenu) {
                    hideCreateMenu();
                } else {
                    showCreateMenu();
                }
                break;
            default:
                int i = (int) view.getTag();
                if (onRotateItemClickListner != null) {
                    onRotateItemClickListner.onclickMenu(i);
                }
                hideCreateMenuNoAnimation();
                break;
        }
    }

    public void showCreateMenu() {
        isShowMenu = true;
        viewCreateAll.setVisibility(VISIBLE);
        backgroudView.setVisibility(VISIBLE);
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mIvCenterAdd.setRotation((centerRotate) * value);
                for (int i = 0; i < rotateMenuItemList.size(); i++) {
                    rotateMenuItemList.get(i).setTopImgVisibility(VISIBLE);
                    rotateMenuItemList.get(i).setTopImgAnimate(value);
                    rotateMenuItemList.get(i).setTranslationX(i, verticalMargin, value);
                    rotateMenuItemList.get(i).setTranslationY(i, horizontalMargin, value);
                }
            }
        });
        animator.addListener(new SimpleAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mIvCenterAdd.clearAnimation();
                for (RotateMenuItem rotateMenuItem : rotateMenuItemList) {
                    rotateMenuItem.clearTopAnimation();
                    rotateMenuItem.setBottomTextVisibility(View.VISIBLE);
                    rotateMenuItem.setBottomTextAlpha(1);
                    rotateMenuItem.clearAnimation();
                }

            }
        });
        animator.start();
    }

    public void hideCreateMenu() {
        isShowMenu = false;
        for (RotateMenuItem rotateMenuItem : rotateMenuItemList) {
            rotateMenuItem.setBottomTextAlpha(0);
        }
        ValueAnimator animator = ValueAnimator.ofFloat(1, 0);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mIvCenterAdd.setRotation((centerRotate) * value);
                for (int i = 0; i < rotateMenuItemList.size(); i++) {
                    rotateMenuItemList.get(i).setTopImgAnimate(value);
                    rotateMenuItemList.get(i).setTranslationX(i, verticalMargin, value);
                    rotateMenuItemList.get(i).setTranslationY(i, horizontalMargin, value);
                }
            }
        });
        animator.addListener(new SimpleAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mIvCenterAdd.clearAnimation();
                viewCreateAll.setVisibility(GONE);
                backgroudView.setVisibility(GONE);
                for (RotateMenuItem rotateMenuItem : rotateMenuItemList) {
                    rotateMenuItem.clearTopAnimation();
                    rotateMenuItem.setTopImgVisibility(View.GONE);
                    rotateMenuItem.setBottomTextVisibility(View.INVISIBLE);
                    rotateMenuItem.clearAnimation();
                }

            }
        });
        animator.start();
    }

    private void hideCreateMenuNoAnimation() {
        isShowMenu = false;
        viewCreateAll.setVisibility(GONE);
        backgroudView.setVisibility(GONE);

        for (RotateMenuItem rotateMenuItem : rotateMenuItemList) {
            rotateMenuItem.setTopImgVisibility(View.GONE);
            rotateMenuItem.setBottomTextVisibility(View.INVISIBLE);
        }
        mIvCenterAdd.setRotation(VISIBLE);
    }
}
