package com.example.eattolife.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eattolife.exercise.ExerciseItem;
import com.example.eattolife.R;

import java.util.ArrayList;
import java.util.List;

public class YunDongKLLB extends AppCompatActivity {

    EditText editTextChaXunYD;
    ImageButton buttonChaXunYD;
    ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yun_dong_kklb);

        editTextChaXunYD = findViewById(R.id.ChaXunYD);
        buttonChaXunYD = findViewById(R.id.bt_ChaXunYD);
        listView = findViewById(R.id.YunDongKLLB);

        // Initialize static list of exercises
        List<ExerciseItem> allExercises = initializeExerciseList();
        List<String> exerciseDescriptions = new ArrayList<>();
        for (ExerciseItem item : allExercises) {
            exerciseDescriptions.add(item.getName() + " - " + item.getCalories() + "千卡/60分钟");
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, exerciseDescriptions);
        listView.setAdapter(adapter);

        buttonChaXunYD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String exerciseName = editTextChaXunYD.getText().toString();
                if (!exerciseName.isEmpty()) {
                    List<String> filteredDescriptions = filterExercises(exerciseName, allExercises);
                    adapter.clear();
                    adapter.addAll(filteredDescriptions);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getApplicationContext(), "请输入运动名称", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private List<ExerciseItem> initializeExerciseList() {
        List<ExerciseItem> exercises = new ArrayList<>();
        exercises.add(new ExerciseItem("慢跑",423 ));
        exercises.add(new ExerciseItem("快跑", 585));
        exercises.add(new ExerciseItem("散步", 100));
        exercises.add(new ExerciseItem("快走", 254));
        exercises.add(new ExerciseItem("爬山", 539));
        exercises.add(new ExerciseItem("慢跑",423 ));
        exercises.add(new ExerciseItem("有氧舞蹈", 423));
        exercises.add(new ExerciseItem("游泳", 423));
        exercises.add(new ExerciseItem("瑜伽", 154));
        exercises.add(new ExerciseItem("保龄球", 154));
        exercises.add(new ExerciseItem("乒乓球",231 ));
        exercises.add(new ExerciseItem("排球", 231));
        exercises.add(new ExerciseItem("羽毛球", 346));
        exercises.add(new ExerciseItem("篮球", 423));
        exercises.add(new ExerciseItem("网球", 462));
        exercises.add(new ExerciseItem("足球",539 ));
        exercises.add(new ExerciseItem("仰卧起坐", 462));
        exercises.add(new ExerciseItem("深蹲", 500));
        exercises.add(new ExerciseItem("卷腹", 500));
        exercises.add(new ExerciseItem("俯卧撑", 577));
        exercises.add(new ExerciseItem("拳击", 616));
        exercises.add(new ExerciseItem("跳绳", 680));
        return exercises;
    }

    private List<String> filterExercises(String query, List<ExerciseItem> exercises) {
        List<String> filtered = new ArrayList<>();
        for (ExerciseItem item : exercises) {
            if (item.getName().toLowerCase().contains(query.toLowerCase())) {
                filtered.add(item.getName() + " - " + item.getCalories() + "千卡/60分钟");
            }
        }
        return filtered;
    }
}


