package com.example.eattolife.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

    // 显示短时长的Toast
    public static void showToastShort(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    // 显示长时长的Toast
    public static void showToastLong(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
