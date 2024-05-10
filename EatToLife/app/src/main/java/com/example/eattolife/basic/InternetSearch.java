package com.example.eattolife.basic;
import com.example.eattolife.R;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class InternetSearch extends AppCompatActivity {

    private ImageButton Btn1,Btn2,Btn3,Btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);

        Btn1=findViewById(R.id.btn_1);
        final Uri uri1=Uri.parse("https://portal.shmtu.edu.cn/node");
        Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW,uri1);
                startActivity(intent);
            }
        });

        Btn2=findViewById(R.id.btn_2);
        final Uri uri2=Uri.parse("https://www.youlai.cn/ask/2F8AE4gqxzl.html");
        Btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW,uri2);
                startActivity(intent);
            }
        });
        Btn3=findViewById(R.id.btn_3);
        final Uri uri3=Uri.parse("https://www.boohee.com/food/");
        Btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW,uri3);
                startActivity(intent);
            }
        });
        Btn4=findViewById(R.id.btn_4);
        final Uri uri4=Uri.parse("https://wenda.so.com/q/1619852973215053");
        Btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW,uri4);
                startActivity(intent);
            }
        });
    }
}
