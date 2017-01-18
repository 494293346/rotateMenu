package cn.asheng.rotateMenu;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.asheng.rotateMenu.view.RotateMenuItem;
import cn.asheng.rotateMenu.view.OnCenterAddClickAdd;
import cn.asheng.rotateMenu.view.RotateMenuView;
import cn.asheng.rotateMenu.view.OnRotateItemClickListner;

public class MainActivity extends AppCompatActivity {
    private RotateMenuView rotateMenuView;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initView();
    }

    private void initView() {
        rotateMenuView = (RotateMenuView) findViewById(R.id.view_create);

        RotateMenuItem rotateMenuItem1 =new RotateMenuItem(mContext);
        rotateMenuItem1.setTopImageRes(R.mipmap.home_publish_erwei_ic);
        rotateMenuItem1.setBottomTextstr("选项一");

        RotateMenuItem rotateMenuItem2 =new RotateMenuItem(mContext);
        rotateMenuItem2.setTopImageRes(R.mipmap.home_publish_edite_advanter_ic);
        rotateMenuItem2.setBottomTextstr("选项二");

        RotateMenuItem rotateMenuItem3 =new RotateMenuItem(mContext);
        rotateMenuItem3.setTopImageRes(R.mipmap.home_publish_tuiguang_ic);
        rotateMenuItem3.setBottomTextstr("选项三");

        RotateMenuItem rotateMenuItem4 =new RotateMenuItem(mContext);
        rotateMenuItem4.setTopImageRes(R.mipmap.home_publish_yyap_ic);
        rotateMenuItem4.setBottomTextstr("选项四");


        List<RotateMenuItem> list=new ArrayList<>();
        list.add(rotateMenuItem1);
        list.add(rotateMenuItem2);
        list.add(rotateMenuItem3);
        list.add(rotateMenuItem4);

        rotateMenuView.setCreateItems(list);

        rotateMenuView.setOnRotateItemClickListner(new OnRotateItemClickListner() {
            @Override
            public void onclickMenu(int i) {
                Toast.makeText(mContext,"选项"+(i+1)+"被点击了",Toast.LENGTH_SHORT).show();
            }
        });

        //如果需要监听中间“加号”按钮，请自行重写
//        rotateMenuView.setOnCenterAddClickAdd(new OnCenterAddClickAdd() {
//            @Override
//            public void onclickAdd() {
//                Toast.makeText(mContext,"加号被点击了",Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
