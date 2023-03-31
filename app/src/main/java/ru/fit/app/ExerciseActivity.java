package ru.fit.app;

import android.annotation.SuppressLint;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import ru.fit.app.models.DataBase;
import ru.fit.app.models.Exercise;
import ru.fit.app.models.Workout;

public class ExerciseActivity extends AppCompatActivity {

    private Exercise currentExercise;

    private TextView exerciseProgress;
    private TextView exerciseApproach;
    private Button exerciseNextButton;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_activity);

        this.currentExercise = findCurrentExercise();

        TextView exerciseName = findViewById(R.id.tv_action);
        ImageView exerciseImage = findViewById(R.id.iv_action);
        TextView exerciseInfo = findViewById(R.id.tv_current_exercise_info);
        exerciseProgress = findViewById(R.id.tv_progress);
        exerciseApproach = findViewById(R.id.tv_approach);
        exerciseNextButton = findViewById(R.id.b_next_action);

        assert currentExercise != null;
        exerciseName.setText(currentExercise.getName());
        exerciseImage.setImageResource(currentExercise.getImageId());
        exerciseInfo.setText(currentExercise.getDescription());
        setCount();

        exerciseNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!currentExercise.setNextCount()){
                    currentExercise.setCount(4);
                    currentExercise.setCompleted(true);
                    Intent intent = new Intent(ExerciseActivity.this, TrainingActivity.class);
                    startActivity(intent);
                }else{
                    setCount();
                }
            }
        });
    }

    private Exercise findCurrentExercise(){
        Exercise currentExercise = null;
        for(Workout workout : DataBase.getInstance().getWorkouts()){
            if(workout.isChoose()) {
                for (Exercise exercise : workout.getExercises()) {
                    if(exercise.isChoose()){
                        currentExercise = exercise;
                        break;
                    }
                }
            }
        }
        return currentExercise;
    }

    @SuppressLint("SetTextI18n")
    private void setCount(){
        exerciseProgress.setText("Текущий подход " + currentExercise.getCount() + " из 4");
        switch (currentExercise.getCount()){
            case 1:
                exerciseApproach.setText("Начнем тренеровку");
                break;
            case 2:
                exerciseApproach.setText("Один подход позади, только вперед");
                break;
            case 3:
                exerciseApproach.setText("Остался последний, молодец");
                break;
            case 4:
                exerciseApproach.setText("Крайний раз настал, глубокий вдох и в бой");
                break;
        }
        if(currentExercise.getCount() == 4){
            exerciseNextButton.setText("Over");
        }
    }
}