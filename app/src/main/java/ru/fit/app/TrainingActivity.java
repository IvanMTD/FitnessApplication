package ru.fit.app;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import ru.fit.app.adapters.ExerciseAdapter;
import ru.fit.app.models.DataBase;
import ru.fit.app.models.Workout;

public class TrainingActivity extends AppCompatActivity {

    private Workout currentWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        currentWorkout = findCurrentWorkout();

        TextView workoutName = findViewById(R.id.tv_action_list);
        ImageView imageTrainingList = findViewById(R.id.iw_training);
        RecyclerView rvExercisesList = findViewById(R.id.rv_exercise_list);
        LinearLayoutManager llm = new LinearLayoutManager(this);

        assert currentWorkout != null;
        workoutName.setText(currentWorkout.getName());
        imageTrainingList.setImageResource(R.drawable.image_training_list);
        rvExercisesList.setLayoutManager(llm);
        rvExercisesList.setHasFixedSize(true);
        rvExercisesList.setAdapter(new ExerciseAdapter(this));
        if(currentWorkout.checkExercisesCompleted()){
            showAlert();
        }
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

    private void showAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(TrainingActivity.this);
        builder
                .setTitle("Внимание")
                .setMessage("Вы выполнили все задания")
                .setCancelable(false)
                .setPositiveButton("Завершить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        currentWorkout.setCompleted(true);
                        Intent intent = new Intent(TrainingActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private Workout findCurrentWorkout(){
        Workout w = null;
        for(Workout workout : DataBase.getInstance().getWorkouts()){
            if (workout.isChoose()){
                w = workout;
                break;
            }
        }
        return w;
    }
}