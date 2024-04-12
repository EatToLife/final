package com.example.eattolife.tools;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.eattolife.R;
import com.example.eattolife.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class SpinnerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //下拉框
    private Spinner sp_a1;
    //定义显示的数据集合和条目布局
    private final static String[] haiXinA1 = {"自选食堂","面条1号窗口","砂锅2号窗口"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        //声明下拉框
        sp_a1 = findViewById(R.id.sp_a1);
        //声明下拉框的数组适配器 & 条目布局
        ArrayAdapter<String> FanAdapter = new ArrayAdapter<>(this, R.layout.item_select, haiXinA1);
        //下拉框默认设置第一项
        sp_a1.setSelection(0);
        //下拉框设置监听器
        sp_a1.setOnItemSelectedListener(this);
        //列表中
//        List<String> list = new ArrayList<String>();
//        list.add("自选食堂");
//        list.add("面条1号窗口");
//        list.add("砂锅2号窗口");
//        list.add("卤味6号窗口");
//        list.add("拌面窗口");
//        list.add("煲仔类窗口");
//        list.add("蒸菜16号窗口");
//        list.add("铁板炒饭");
//        list.add("盖浇饭窗口");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ToastUtil.showToastShort(this,"您的选择是" + haiXinA1[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}