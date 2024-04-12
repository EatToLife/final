package com.example.eattolife.basic;

import androidx.annotation.NonNull;
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

import com.example.eattolife.FoodDao;
import com.example.eattolife.FoodRecord;
import com.example.eattolife.LvFoodRecordAdapter;
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


    /**
     * 自定义添加
     */
    private List<FoodRecord> foodRecordList; //食物数据集合
    private LvFoodRecordAdapter lvFoodRecordAdapter; //食物信息数据适配器

    private ListView lv_foodRecord; //用户饮食列表

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
                int count = (Integer)msg.obj;
                tv_food_count.setText("共有"+count+"可供选择");
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yin_shi_tj);

        /**
         * 返回
         */
        //获得返回图标
        TextView back1 = findViewById(R.id.back);
        Typeface font = Typeface.createFromAsset(getAssets(),"back.ttf");
        back1.setTypeface(font);

        //跳转按钮
        Button back2 = findViewById(R.id.back);
        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(YinShiTJ.this, Wo.class);
                startActivity(intent);
            }
        });

        initView(); //单个条目查询
        loadFoodDb(); //加载食物信息

    }

    private void initView() {
        haixin_a2 = findViewById(R.id.haixin_a2);
        tv_food_count = findViewById(R.id.tv_food_count);

        lv_foodRecord = findViewById(R.id.lv_foodRecord);
        mainHandler = new Handler(getMainLooper());

        foodDao = new FoodDao(); //数据库操作类
        dbOpenHelper = new DbOpenHelper();
        mainHandler = new Handler(getMainLooper()); //获取主线程

        haixin_a2.setOnClickListener(this);
    }

    private void loadFoodDb() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                foodRecordList = foodDao.getAllFoodList(); //获取所有的食物数据
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        showLvData();
                    }
                });
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.haixin_a2) {
            doQueryCount();
        }
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

    //显示列表数据的方法
    private void showLvData() {
        if (lvFoodRecordAdapter == null) {      //首次加载时的操作
            lvFoodRecordAdapter = new LvFoodRecordAdapter(this, foodRecordList);
            lv_foodRecord.setAdapter(lvFoodRecordAdapter);
        }else {     //更新数据时的操作
            lvFoodRecordAdapter.setFoodRecordList(foodRecordList);
            lvFoodRecordAdapter.notifyDataSetChanged();
        }

        //修改按钮的操作
        lvFoodRecordAdapter.setOnEditBtnClickListener(new OnEditBtnClickListener() {
            @Override
            public void onEditBtnClick(View v, int position) {
                //删除方法
            }
        });

        //删除按钮的操作
        lvFoodRecordAdapter.setOnDelBtnClickListener(new OnDelBtnClickListener() {
            @Override
            public void onDelBtnClick(View v, int position) {
                //删除方法
            }
        });
    }

}