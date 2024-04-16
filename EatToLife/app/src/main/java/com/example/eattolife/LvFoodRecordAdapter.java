package com.example.eattolife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class LvFoodRecordAdapter extends BaseAdapter {
    private Context context; //上下文信息
    private List<FoodRecord> foodRecordList; //用户饮食记录集合

    private OnEditBtnClickListener onEditBtnClickListener; //修改按钮点击事件的监听实例
    private OnDelBtnClickListener onDelBtnClickListener; //删除按钮点击事件的监听实例

    public LvFoodRecordAdapter() {
    }

    public LvFoodRecordAdapter(Context context, List<FoodRecord> foodRecordList) {
        this.context = context;
        this.foodRecordList = foodRecordList;
    }

    public void setFoodRecordList(List<FoodRecord> foodRecordList) {
        this.foodRecordList = foodRecordList;
    }

    public void setOnEditBtnClickListener(OnEditBtnClickListener onEditBtnClickListener) {
        this.onEditBtnClickListener = onEditBtnClickListener;
    }

    public void setOnDelBtnClickListener(OnDelBtnClickListener onDelBtnClickListener) {
        this.onDelBtnClickListener = onDelBtnClickListener;
    }

    @Override
    public int getCount() {
        return foodRecordList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodRecordList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.food_record_item, null);
            //food_list_item.xml加载条目布局，赋值给convertView变量 & 数据集合
            viewHolder = new ViewHolder();

            viewHolder.foodRecordID = convertView.findViewById(R.id.foodRecordID);
            viewHolder.foodRecordName = convertView.findViewById(R.id.foodRecordName);
            viewHolder.foodRecordCalorie = convertView.findViewById(R.id.foodRecordCalorie);

            //viewHolder.iv_foodPic = convertView.findViewById(R.id.iv_foodPic);
            viewHolder.deleteFoodRecord = convertView.findViewById(R.id.deleteFoodRecord);
            viewHolder.addFoodRecord = convertView.findViewById(R.id.addFoodRecord);

            convertView.setTag(viewHolder); //给convertView列表视图做viewHolder标记
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //进行数据填充
        final FoodRecord item = foodRecordList.get(position);
        viewHolder.foodDate.setText(item.getFoodDate());
        viewHolder.foodMeal.setText(item.getFoodMeal());
        viewHolder.foodCalorie.setText(String.format(Locale.getDefault(), "%.1f.", item.getFoodCalorie()));
        //viewHolder.foodPic.setImageResource(R.drawable.your_image_name); //图片加载

        //修改按钮的点击事件
        viewHolder.iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onEditBtnClickListener != null) {
                    onEditBtnClickListener.onEditBtnClick(v, position);
                }            }
        });

        //删除按钮的点击事件
        viewHolder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDelBtnClickListener != null) {
                    onDelBtnClickListener.onDelBtnClick(v, position);
                }            }
        });

        return convertView;
    }

    //自定义内部类
    private class ViewHolder {
        private TextView foodRecordID, foodRecordName, foodRecordCalorie;
        private ImageView deleteFoodRecord, addFoodRecord;
    }
}