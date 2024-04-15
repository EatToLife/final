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
    private Context context; //��������Ϣ
    private List<FoodInfo> foodInfoList; //�û���ʳ��¼����

    private OnEditBtnClickListener onEditBtnClickListener; //�޸İ�ť����¼��ļ���ʵ��
    private OnDelBtnClickListener onDelBtnClickListener; //ɾ����ť����¼��ļ���ʵ��

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
            //food_list_item.xml������Ŀ���֣���ֵ��convertView���� & ���ݼ���
            viewHolder = new ViewHolder();

            viewHolder._foodName = convertView.findViewById(R.id._foodName);
            viewHolder._foodPrice = convertView.findViewById(R.id._foodPrice);
            viewHolder._foodCalorie = convertView.findViewById(R.id._foodCalorie);

            //viewHolder.iv_foodPic = convertView.findViewById(R.id.iv_foodPic);
            viewHolder._edit = convertView.findViewById(R.id._edit);
            viewHolder._delete = convertView.findViewById(R.id._delete);

            convertView.setTag(viewHolder); //��convertView�б���ͼ��viewHolder���
        } else {
            viewHolder = (ViewHolder) convertView.getTag(); //����Ѿ����������
        }

        //�����������
        final FoodInfo item = foodInfoList.get(position);
        // ת��Ϊ�ַ���!!!String from int & float
        viewHolder._foodName.setText(item.getFoodName());
        viewHolder._foodPrice.setText(String.valueOf(item.getFoodPrice()));
        viewHolder._foodCalorie.setText(String.format(Locale.getDefault(), "%.1f.", item.getFoodCalorie()));
        //viewHolder.foodPic.setImageResource(R.drawable.your_image_name); //ͼƬ����

        //�޸İ�ť�ĵ���¼�
        viewHolder._edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditBtnClickListener.onEditBtnClick(v, position);
            }
        });

        //ɾ����ť�ĵ���¼�
        viewHolder._delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDelBtnClickListener.onDelBtnClick(v, position);
            }
        });

        return convertView;
    }

    //�Զ����ڲ���
    private class ViewHolder {
        private TextView _foodName, _foodPrice, _foodCalorie;
        private ImageView _edit, _delete;
    }
}