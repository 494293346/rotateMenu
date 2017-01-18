package cn.asheng.rotateMenu.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.asheng.rotateMenu.R;
import cn.asheng.rotateMenu.utils.ViewUtils;

/**
 * Created by Administrator on 2016/12/5.
 */

public class RotateMenuItem extends RelativeLayout {
    private static final int DEFULT_IMG_SRC = R.mipmap.home_publish_erwei_ic;//默认图片
    private static final int DEFULT_IMG_WIDTH = 80;//图片默认宽度
    private static final int DEFULT_IMG_HEIGH = 80;//图片默认高度
    private static final int DEFULT_TEXT_SIZE = 14;//文字大小
    private static final int DEFULT_TEXT_COLOR = Color.BLACK;//文字颜色
    private static final int DEFULT_TEXT_MARGIN = 16;//图文距离
    private static final int DEFULT_ITEM_ROTE = 360;//旋转角度

    private Context mContext;
    private ImageView imgTop;
    private TextView tvBottom;
    private int totate = DEFULT_ITEM_ROTE;

    private Rect itemRct = new Rect();

    public RotateMenuItem(Context context) {
        this(context, null, 0);
    }

    public RotateMenuItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RotateMenuItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        LayoutInflater.from(mContext).inflate(R.layout.view_rotate_menu_item, this, true);
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.RotateMenuItem);
        int imgSrc = typedArray.getResourceId(R.styleable.RotateMenuItem_img_src, DEFULT_IMG_SRC);
        float imgWidth = typedArray.getDimension(R.styleable.RotateMenuItem_img_width, ViewUtils.dip2px(mContext, DEFULT_IMG_WIDTH));
        float imgHeight = typedArray.getDimension(R.styleable.RotateMenuItem_img_height, ViewUtils.dip2px(mContext, DEFULT_IMG_HEIGH));
        float bottom_text_size = typedArray.getDimension(R.styleable.RotateMenuItem_bottom_text_size, ViewUtils.dip2px(mContext, DEFULT_TEXT_SIZE));
        int textColor = typedArray.getColor(R.styleable.RotateMenuItem_bottom_text_color, DEFULT_TEXT_COLOR);
        float textMargin = typedArray.getDimension(R.styleable.RotateMenuItem_bottom_text_margin, ViewUtils.dip2px(mContext, DEFULT_TEXT_MARGIN));
        String strText = typedArray.getString(R.styleable.RotateMenuItem_bottom_text);
        typedArray.recycle();

        imgTop = (ImageView) findViewById(R.id.imgTop);
        tvBottom = (TextView) findViewById(R.id.tvBottom);
        imgTop.setImageResource(imgSrc);
        LayoutParams mParams = new LayoutParams((int) imgWidth, (int) imgHeight);
        imgTop.setLayoutParams(mParams);
        tvBottom.setTextSize(TypedValue.COMPLEX_UNIT_PX, bottom_text_size);
        tvBottom.setTextColor(textColor);
        tvBottom.setPadding(0, (int) textMargin, 0, 0);
        tvBottom.setText(strText);
    }

    public void setItemRotate(int rotate) {
        this.totate = rotate;
    }

    public void setTextPadding(int textMargin) {
        tvBottom.setPadding(0, textMargin, 0, 0);
    }

    public void setBottomTextstr(String strText) {
        tvBottom.setText(strText);
    }

    public void setBottomTextColor(int textColor) {
        tvBottom.setTextColor(textColor);
    }

    public void setBottomTextSize(int bottom_text_size) {
        tvBottom.setTextSize(TypedValue.COMPLEX_UNIT_PX, bottom_text_size);
    }

    public void setImgTopSize(int imgTopWidth, int imgTopHeight) {
        LayoutParams mParams = new LayoutParams(imgTopWidth, imgTopHeight);
        imgTop.setLayoutParams(mParams);
    }

    public void setTopImageRes(int imgSrc) {
        imgTop.setImageResource(imgSrc);
    }

    public void setTopImgVisibility(int visibility) {
        imgTop.setVisibility(visibility);
    }

    public void setTopImgAnimate(float value) {
        imgTop.setAlpha(value);
        imgTop.setRotation(totate * value);
        imgTop.setScaleX(0.5f + value * 0.5f);
        imgTop.setScaleY(0.5f + value * 0.5f);
    }

    public void setTranslationX(int i, float verticalMargin, float value) {
        if (i % 2 == 0) {
            setTranslationX((-verticalMargin * value) / 2);
        } else {
            setTranslationX((verticalMargin * value) / 2);
        }
    }

    public void setTranslationY(int i, float horizontalMargin, float value) {
        float paddingTop = 0;
        if (i > 1) {
            paddingTop = horizontalMargin * value;
        }
        setPadding(0, (int) paddingTop, 0, 0);
    }

    public void clearTopAnimation() {
        imgTop.clearAnimation();
    }

    public void setBottomTextVisibility(int visibility) {
        tvBottom.setVisibility(visibility);
    }

    public void setBottomTextAlpha(int alpha) {
        tvBottom.setAlpha(alpha);
    }
}
