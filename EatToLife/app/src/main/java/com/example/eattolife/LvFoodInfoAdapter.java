package com.example.eattolife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eattolife.basic.YinShiTJ;

import java.util.List;
import java.util.Locale;

public class LvFoodInfoAdapter extends BaseAdapter {
    private Context context; //上下文信息
    private List<FoodInfo> foodInfoList; //用户饮食记录集合

    private OnEditBtnClickListener onEditBtnClickListener; //修改按钮点击事件的监听实例
    private OnDelBtnClickListener onDelBtnClickListener; //删除按钮点击事件的监听实例

    public LvFoodInfoAdapter(YinShiTJ yinShiTJ, List<FoodInfo> foodInfoList) {
    }

    public void setFoodInfoList(List<FoodInfo> foodInfoList) {
        this.foodInfoList = foodInfoList;
    }

    public void setOnEditBtnClickListener(OnEditBtnClickListener onEditBtnClickListener) {
        this.onEditBtnClickListener = onEditBtnClickListener;
    }

    public void setOnDelBtnClickListener(OnDelBtnClickListener onDelBtnClickListener) {
        this.onDelBtnClickListener = onDelBtnClickListener;
    }

    @Override
    public int getCount() {
        return foodInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.food_list_item, null);
            //food_list_item.xml加载条目布局，赋值给convertView变量 & 数据集合
            viewHolder = new ViewHolder();

            viewHolder._foodName = convertView.findViewById(R.id._foodName);
            viewHolder._foodPrice = convertView.findViewById(R.id._foodPrice);
            viewHolder._foodCalorie = convertView.findViewById(R.id._foodCalorie);

            //viewHolder.iv_foodPic = convertView.findViewById(R.id.iv_foodPic);
            viewHolder.iv_edit = convertView.findViewById(R.id.iv_edit);
            viewHolder.iv_delete = convertView.findViewById(R.id.iv_delete);

            convertView.setTag(viewHolder); //给convertView列表视图做viewHolder标记
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //进行数据填充
        final FoodInfo item = foodInfoList.get(position);
        viewHolder._foodName.setText(item.getFoodName());
        viewHolder._foodPrice.setText(String.valueOf(item.getFoodPrice())); // 转换为字符串
        viewHolder._foodCalorie.setText(String.format(Locale.getDefault(), "%.1f", item.getFoodCalorie())); // 格式化浮点数并转换为字符串
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
        private TextView _foodName, _foodPrice, _foodCalorie;
        private ImageView iv_foodPic, iv_edit, iv_delete;
    }
}