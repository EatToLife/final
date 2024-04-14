package com.example.eattolife;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;

import com.example.eattolife.R;


public class JianKangTJ extends AppCompatActivity implements View.OnClickListener {

    private ImageButton iB_kalulphd;
    private ImageButton iB_tizhong;
    private ImageButton iB_shiwuzb;
    private ImageButton iB_yundongzb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jian_kang_tj);


        iB_kalulphd = findViewById(R.id.Button_kalulphd);
        iB_tizhong = findViewById(R.id.Button_tizhong);
        iB_shiwuzb = findViewById(R.id.Button_shiwuzb);
        iB_yundongzb = findViewById(R.id.Button_yundongzb);
        iB_kalulphd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 显示包含ECharts的AlertDialog
                showEChartsDialog_KaLulphd();
            }
        });
        iB_tizhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 显示包含ECharts的AlertDialog
                showEChartsDialog_TiZhong();
            }
        });
        iB_shiwuzb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 显示包含ECharts的AlertDialog
                showEChartsDialog_ShiWuZB();
            }
        });
        iB_yundongzb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 显示包含ECharts的AlertDialog
                showEChartsDialog_YunDongZB();
            }
        });
    }

    private void showEChartsDialog_KaLulphd() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.activity_ka_lu_lphdtj, null);
        WebView webView = dialogView.findViewById(R.id.chartshow_KaLuLPHDTJ);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl("file:///android_asset/echarts_KaLulphdT.html");

        builder.setView(dialogView)
                .setPositiveButton("关闭", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showEChartsDialog_TiZhong() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.activity_ti_zhong_bmitj, null);
        WebView webView = dialogView.findViewById(R.id.chartshow_TiZhongBMITJ);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl("file:///android_asset/echarts_TiZhongbmiT.html");

        builder.setView(dialogView)
                .setPositiveButton("关闭", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showEChartsDialog_ShiWuZB() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.activity_shi_wu_zbtj, null);
        WebView webView = dialogView.findViewById(R.id.chartshow_ShiWuZBTJ);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl("file:///android_asset/echarts_ShiWuzbT.html");

        builder.setView(dialogView)
                .setPositiveButton("关闭", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showEChartsDialog_YunDongZB() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.activity_yun_dong_zbtj, null);
        WebView webView = dialogView.findViewById(R.id.chartshow_YunDongZBTJ);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl("file:///android_asset/echarts_YunDongzbT.html");

        builder.setView(dialogView)
                .setPositiveButton("关闭", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onClick(View v) {

    }
}