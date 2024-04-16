package com.example.eattolife;

import android.view.View;

/**
 * 删除按钮的点击事件监听接口
 */
public interface OnAddBtnClickListener {
    void onAddBtnClick(View v, int position); //pos触发行元素的位置，删除按钮的点击事件处理
}
