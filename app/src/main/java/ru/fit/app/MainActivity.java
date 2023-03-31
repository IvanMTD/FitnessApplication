package ru.fit.app;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import ru.fit.app.adapters.WorkoutAdapter;
import ru.fit.app.models.DataBase;
import ru.fit.app.models.Workout;

public class MainActivity extends AppCompatActivity {

    private Button buttonNext;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvTrainingList = findViewById(R.id.rv_training_menu);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        buttonNext = findViewById(R.id.b_next_main);

        for(Workout workout : DataBase.getInstance().getWorkouts()){
            if(workout.isChoose()){
                if(workout.isCompleted()){
                    buttonNext.setText("Again");
                    break;
                }
            }
        }

        rvTrainingList.setLayoutManager(llm);
        rvTrainingList.setHasFixedSize(true);
        rvTrainingList.setAdapter(new WorkoutAdapter(buttonNext));

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DataBase.getInstance().getChosenWorkout().isCompleted()){
                    showDialog();
                }else {
                    openNext();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle("Внимание")
                .setMessage("Вы выполнили этот сет упражнений, хотите повторить?")
                .setCancelable(false)
                .setPositiveButton("Повторить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openNext();
                    }
                })
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void openNext(){
        for(Workout workout : DataBase.getInstance().getWorkouts()){
            if(workout.isChoose()) {
                workout.setExerciseDefault();
            }
        }
        Intent trainingActivity = new Intent(MainActivity.this,TrainingActivity.class);
        startActivity(trainingActivity);
    }
}