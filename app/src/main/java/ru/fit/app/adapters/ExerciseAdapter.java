package ru.fit.app.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import ru.fit.app.ExerciseActivity;
import ru.fit.app.R;
import ru.fit.app.models.DataBase;
import ru.fit.app.models.Exercise;
import ru.fit.app.models.Workout;

import java.util.ArrayList;
import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {

    private List<ExerciseViewHolder> viewHolders;
    private Workout workout;

    private Context parent;

    public ExerciseAdapter(Context parent){
        this.parent = parent;
        viewHolders = new ArrayList<>();
        for(Workout workout : DataBase.getInstance().getWorkouts()){
            if(workout.isChoose()){
                this.workout = workout;
                break;
            }
        }
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutForExerciseList = R.layout.rv_activity_exercises;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutForExerciseList, viewGroup, false);

        ExerciseAdapter.ExerciseViewHolder viewHolder = new ExerciseAdapter.ExerciseViewHolder(view);
        viewHolders.add(viewHolder);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Exercise exercise = workout.getExercises().get(position);
        holder.getExerciseName().setText(exercise.getName());
        holder.getExerciseInfo().setText(exercise.getInformation());
        holder.getExerciseMotivation().setImageResource(exercise.getImageId());
        if(exercise.isCompleted()){
            holder.getExerciseCompleted().setImageResource(R.drawable.star_fill);
        }else {
            holder.getExerciseCompleted().setImageResource(R.drawable.star);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(workout.getExercises().get(position).isCompleted()){
                    prepare(position);
                    showDialog();
                }else {
                    prepare(position);
                    Intent exerciseActivity = new Intent(parent, ExerciseActivity.class);
                    parent.startActivity(exerciseActivity);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return workout.getExercises().size();
    }

    public Workout getWorkout() {
        return workout;
    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(parent);
        builder
                .setTitle("Внимание")
                .setMessage("Вы выполнили это упражнение, хотите повторить?")
                .setCancelable(false)
                .setPositiveButton("Повторить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        workout.setExerciseDefault();
                        Intent exerciseActivity = new Intent(parent, ExerciseActivity.class);
                        parent.startActivity(exerciseActivity);
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

    private void prepare(int position){
        for (int i = 0; i < viewHolders.size(); i++) {
            workout.getExercises().get(i).setChoose(false);
        }
        workout.getExercises().get(position).setChoose(true);
    }

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout background;
        private TextView exerciseName;
        private TextView exerciseInfo;
        private ImageView exerciseMotivation;
        private ImageView exerciseCompleted;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);

            background = itemView.findViewById(R.id.cl_exercises_struct);
            exerciseName = itemView.findViewById(R.id.tv_exercise_name);
            exerciseInfo = itemView.findViewById(R.id.tv_exercise_info);
            exerciseMotivation = itemView.findViewById(R.id.iv_exercise_motivation);
            exerciseCompleted = itemView.findViewById(R.id.iv_exercise_completed);
        }

        public ConstraintLayout getBackground() {
            return background;
        }

        public TextView getExerciseName() {
            return exerciseName;
        }

        public TextView getExerciseInfo() {
            return exerciseInfo;
        }

        public ImageView getExerciseMotivation() {
            return exerciseMotivation;
        }

        public ImageView getExerciseCompleted() {
            return exerciseCompleted;
        }
    }
}
