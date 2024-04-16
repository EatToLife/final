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
    private OnAddBtnClickListener onAddBtnClickListener; //添加按钮点击事件的监听实例


    public LvFoodInfoAdapter(Context context, List<FoodInfo> foodInfoList) {
        this.context = context;
        this.foodInfoList = foodInfoList;
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

    public void setOnAddBtnClickListener(OnAddBtnClickListener onAddBtnClickListener) {
        this.onAddBtnClickListener = onAddBtnClickListener;
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

            viewHolder._foodID = convertView.findViewById(R.id._foodID);
            viewHolder._foodName = convertView.findViewById(R.id._foodName);
            viewHolder._foodPrice = convertView.findViewById(R.id._foodPrice);
            viewHolder._foodCalorie = convertView.findViewById(R.id._foodCalorie);

            //viewHolder.iv_foodPic = convertView.findViewById(R.id.iv_foodPic);
            viewHolder._edit = convertView.findViewById(R.id._edit);
            viewHolder._delete = convertView.findViewById(R.id._delete);
            viewHolder._add = convertView.findViewById(R.id._add);

            convertView.setTag(viewHolder); //给convertView列表视图做viewHolder标记
        } else {
            viewHolder = (ViewHolder) convertView.getTag(); //获得已经保存的内容
        }

        //进行数据填充
        final FoodInfo item = foodInfoList.get(position);
        // 转换为字符串!!!String from int & float
        viewHolder._foodID.setText(String.valueOf(item.getFoodID()));
        viewHolder._foodName.setText(item.getFoodName());
        viewHolder._foodPrice.setText(String.valueOf(item.getFoodPrice()));
        viewHolder._foodCalorie.setText(String.format(Locale.getDefault(), "%.1f.", item.getFoodCalorie()));
        //viewHolder.foodPic.setImageResource(R.drawable.your_image_name); //图片加载

        //修改按钮的点击事件
        viewHolder._edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditBtnClickListener.onEditBtnClick(v, position);
            }
        });

        //删除按钮的点击事件
        viewHolder._delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDelBtnClickListener.onDelBtnClick(v, position);
            }
        });

        //添加按钮的点击事件
        viewHolder._add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddBtnClickListener.onAddBtnClick(v, position);
            }
        });


        return convertView;
    }

    //自定义内部类
    private class ViewHolder {
        private TextView _foodName, _foodPrice, _foodCalorie, _foodID;
        private ImageView _edit, _delete, _add;
    }
}