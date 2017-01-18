# rotateMenu
仿闲鱼首页，“底部加号弹出菜单选项” 界面，动画效果可能跟闲鱼有点不一样
##简介
 ```
仿闲鱼首页，“底部加号弹出菜单选项” 界面，动画效果可能跟闲鱼有点不一样，详情请看效果图，用模拟器有点卡，真机效果要好一些~~~
 具体效果如下图：
```
##效果图展示
![图片描述](https://github.com/494293346/rotateMenu/blob/master/images/image.gif)
##用法
 ```
 1、请将所有相关的文件拷贝到自己的项目中，这个就不多解释了。。。
 
 2、在xml界面中加入该控件
  <cn.asheng.rotateMenu.view.RotateMenuView
        android:id="@+id/view_create"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        lee:back_ground_color="#f2ffffff"   背景颜色
        lee:center_add_src="@mipmap/add_publish_fragment_bg"  中间加号按钮
        lee:center_rotate="225"    加号按钮点击后的旋转角度
        lee:horizontal_margin="30dp"   菜单各个选项的横向距离
        lee:item_rotate="360"      各个菜单子选项弹出时的旋转角度，这边我比较低调只转了360度 0.0
        lee:vertical_margin="50dp"   菜单各个选项的纵向距离
        ></cn.asheng.rotateMenu.view.RotateMenuView>
        
 2、在相关java代码中
 定义子选项
        RotateMenuItem rotateMenuItem1 =new RotateMenuItem(mContext);
        rotateMenuItem1.setTopImageRes(R.mipmap.home_publish_erwei_ic); 子选项图片
        rotateMenuItem1.setBottomTextstr("选项一");  子选项下方文字描述
        
        RotateMenuItem rotateMenuItem2 =new RotateMenuItem(mContext);
        rotateMenuItem2.setTopImageRes(R.mipmap.home_publish_edite_advanter_ic);
        rotateMenuItem2.setBottomTextstr("选项二");
        
        为了看起来美观，请放偶数个子选项，奇数个没做处理，会挺难看的 = = 
        
          将子选项加入集合
           List<RotateMenuItem> list=new ArrayList<>();
           list.add(rotateMenuItem1);
           list.add(rotateMenuItem2);
           
           将集合放入控件
            rotateMenuView.setCreateItems(list);
            
            设置相关监听
            子选项被点击
             rotateMenuView.setOnRotateItemClickListner(new OnRotateItemClickListner() {
            @Override
            public void onclickMenu(int i) {
                Toast.makeText(mContext,"选项"+(i+1)+"被点击了",Toast.LENGTH_SHORT).show();
            }
        });
          中间“加号被点击监听” 如果不设置将为默认效果：弹出菜单栏或者隐藏菜单栏
           rotateMenuView.setOnCenterAddClickAdd(new OnCenterAddClickAdd() {
            @Override
            public void onclickAdd() {
                Toast.makeText(mContext,"加号被点击了",Toast.LENGTH_SHORT).show();
            }
        });
          
 还有更多用法，请自行下载参考
 
3、如果你需要更换相应控件按钮的大小，请到对应的xml文件自行修改
```
