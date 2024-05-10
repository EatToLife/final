package com.example.eattolife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eattolife.user.Userinfo;

import java.util.List;
import java.util.Locale;

public class LvUserinfoAdapter extends BaseAdapter {
    private Context context; //上下文信息
    private List<Userinfo> userinfoList; //用户信息数据集合

    private OnEditBtnClickListener onEditBtnClickListener; //修改按钮点击事件的监听实例
    private OnDelBtnClickListener onDelBtnClickListener; //删除按钮点击事件的监听实例
    private ViewHolder viewHolder;

    public LvUserinfoAdapter() {
    }

    public LvUserinfoAdapter(Context context, List<Userinfo> userinfoList) {
        this.context = context;
        this.userinfoList = userinfoList;
    }

    public void setUserinfoList(List<Userinfo> userinfoList) {
        this.userinfoList = userinfoList;
    }

    /*public void setOnEditBtnClickListener(OnEditBtnClickListener onEditBtnClickListener) {
        this.onEditBtnClickListener = onEditBtnClickListener;
    }

    public void setOnDelBtnClickListener(OnDelBtnClickListener onDelBtnClickListener) {
        this.onDelBtnClickListener = onDelBtnClickListener;
    }*/

    @Override
    public int getCount() {
        return userinfoList.size();
    }//返回所有数据的大小

    @Override
    public Object getItem(int position) {
        return userinfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_userinfo, parent, false);
            //food_list_item.xml加载条目布局，赋值给convertView变量 & 数据集合
            viewHolder = new ViewHolder();

            viewHolder.tv_age = convertView.findViewById(R.id.tv_age);
            viewHolder.tv_sex = convertView.findViewById(R.id.tv_sex);
            viewHolder.tv_height = convertView.findViewById(R.id.tv_height);
            viewHolder.tv_weight = convertView.findViewById(R.id.tv_weight);
            viewHolder.tv_foodLike = convertView.findViewById(R.id.tv_foodLike);
            viewHolder.tv_sportLike = convertView.findViewById(R.id.tv_sportLike);
            //viewHolder.iv_edit = convertView.findViewById(R.id.iv_edit);
            convertView.setTag(viewHolder); //给convertView列表视图做viewHolder标记
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //进行数据填充
        final Userinfo item = userinfoList.get(position);
        viewHolder.tv_age.setText("年龄："+String.valueOf(item.getAge()));
        viewHolder.tv_sex.setText("性别："+item.getSex());
        viewHolder.tv_height.setText("身高："+String.format(Locale.getDefault(), "%.1f", item.getHeight()));
        viewHolder.tv_weight.setText("体重："+String.format(Locale.getDefault(), "%.2f", item.getWeight()));
        viewHolder.tv_foodLike.setText("喜欢的食物： "+item.getFoodLike());
        viewHolder.tv_sportLike.setText("喜欢的运动： "+item.getSportLike());
        //viewHolder.foodPic.setImageResource(R.drawable.your_image_name); //图片加载

        //修改按钮的点击事件
        /*
        viewHolder.iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onEditBtnClickListener != null) {
                    onEditBtnClickListener.onEditBtnClick(v, position);
                }
            }
        });*/


        //删除按钮的点击事件
        /*
        viewHolder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDelBtnClickListener != null) {
                    onDelBtnClickListener.onDelBtnClick(v, position);
                }            }
        });*/

        return convertView;
    }

    //自定义内部类
    private class ViewHolder {
        private TextView tv_age, tv_sex, tv_height, tv_weight, tv_foodLike, tv_sportLike;
        //private ImageView  iv_edit;
    }
}
