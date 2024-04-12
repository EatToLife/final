package com.example.eattolife;

import android.view.View;

/**
 * 修改按钮的点击事件监听接口
 */
public interface OnEditBtnClickListener {
    void onEditBtnClick(View v, int position); //pos触发行元素的位置，修改按钮的点击事件处理
}
