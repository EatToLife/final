package com.example.eattolife.basic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.eattolife.FoodAddActivity;
import com.example.eattolife.FoodDao;
import com.example.eattolife.FoodInfo;
import com.example.eattolife.LvFoodInfoAdapter;
import com.example.eattolife.OnDelBtnClickListener;
import com.example.eattolife.OnEditBtnClickListener;
import com.example.eattolife.sql.DbOpenHelper;
import com.example.eattolife.sql.MySqlHelp;
import com.example.eattolife.R;

import java.util.List;

public class YinShiTJ extends AppCompatActivity implements View.OnClickListener {
    /**
     * 单个条目查询
     */
    private Button haixin_a2; //查询海鑫楼食物数量的按钮
    private TextView tv_food_count; //查询食物数量的文本框

    private List<FoodInfo> foodInfoList; //食物信息集合
    //private List<FoodRecord> foodRecordList; //饮食记录集合
    //private LvFoodRecordAdapter lvFoodRecordAdapter; //食物信息数据适配器
    LvFoodInfoAdapter lvFoodInfoAdapter; //食物信息数据适配器

    private ListView lv_foodInfo; //用户饮食列表

    private FoodDao foodDao; //用户自定义添加食物的数据库操作类
    private DbOpenHelper dbOpenHelper; //数据库连接辅助类
    private Handler mainHandler; //主线程

    //复写线程
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            //super.handleMessage(msg);
            ;
            if (msg.what == 0) {
                int count = (Integer) msg.obj;
                tv_food_count.setText("共有" + count + "可供选择");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yin_shi_tj);

        //获得返回图标
        TextView back1 = findViewById(R.id.back);
        Typeface font = Typeface.createFromAsset(getAssets(), "back.ttf");
        back1.setTypeface(font);

        //返回跳转按钮
        Button back2 = findViewById(R.id.back);
        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(YinShiTJ.this, Wo.class);
                startActivity(intent);
            }
        });

        //自定义添加饮食
        Button customize = findViewById(R.id.customize);
        customize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(YinShiTJ.this, FoodAddActivity.class);
                startActivity(intent);
            }
        });

        initView(); //单个条目查询
        loadFoodDb(); //加载食物信息

    }

    private void initView() {
        haixin_a2 = findViewById(R.id.haixin_a2);
        tv_food_count = findViewById(R.id.tv_food_count);

        lv_foodInfo = findViewById(R.id.lv_foodInfo);
        mainHandler = new Handler(getMainLooper());

        foodDao = new FoodDao(); //数据库操作类
        dbOpenHelper = new DbOpenHelper();
        mainHandler = new Handler(getMainLooper()); //获取主线程

        haixin_a2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.haixin_a2) {
            doQueryCount();
        } else if (v.getId() == R.id.customize) {
            Intent intent = new Intent(this, FoodAddActivity.class);
            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1) { //操作成功
            loadFoodDb(); //重新加载数据
        }
    }

    /**
     * 食物加载
     */
    private void loadFoodDb() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                foodInfoList = foodDao.getAllFoodList(); //获取所有的食物数据
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        showLvData();
                    }
                });
            }
        }).start();
    }

    /**饮食记录加载
     *
     */
//    private void loadFoodDb() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                foodRecordList = foodDao.getAllFoodList(); //获取所有的食物数据
//                mainHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        showLvData();
//                    }
//                });
//            }
//        }).start();
//    }

    //显示列表数据的方法
    private void showLvData() {
        if (lvFoodInfoAdapter == null) {      //首次加载时的操作
            lvFoodInfoAdapter = new LvFoodInfoAdapter(this, foodInfoList);
            lv_foodInfo.setAdapter(lvFoodInfoAdapter);
        } else {     //更新数据时的操作
            lvFoodInfoAdapter.setFoodInfoList(foodInfoList);
            lvFoodInfoAdapter.notifyDataSetChanged();
        }

        //修改按钮的操作
        lvFoodInfoAdapter.setOnEditBtnClickListener(new OnEditBtnClickListener() {
            @Override
            public void onEditBtnClick(View v, int position) {
                //删除方法
            }
        });

        //删除按钮的操作
        lvFoodInfoAdapter.setOnDelBtnClickListener(new OnDelBtnClickListener() {
            @Override
            public void onDelBtnClick(View v, int position) {
                //删除方法
            }
        });
    }

    //执行查询食物数量的方法
    private void doQueryCount() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int count = MySqlHelp.getFoodSize();
                Message msg = Message.obtain();
                msg.what = 0;
                msg.obj = count;
                //向主线程发送数据
                handler.sendMessage(msg);
            }
        }).start(); //!!start
    }
}