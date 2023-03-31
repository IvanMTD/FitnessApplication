package ru.fit.app.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import ru.fit.app.R;
import ru.fit.app.models.DataBase;
import ru.fit.app.models.Workout;

import java.util.ArrayList;
import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>{
    private List<WorkoutViewHolder> viewHolders;
    private Button bNext;

    public WorkoutAdapter(Button bNext){
        viewHolders = new ArrayList<>();
        this.bNext = bNext;
    }

    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutForWorkoutList = R.layout.rv_activity_trainings;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutForWorkoutList, viewGroup, false);

        WorkoutViewHolder viewHolder = new WorkoutViewHolder(view);
        viewHolders.add(viewHolder);

        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull WorkoutViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Workout workout = DataBase.getInstance().getWorkouts().get(position);
        if(workout.isChoose()){
            viewHolders.get(position).getBackground().setBackgroundColor(0xFF393939);
            viewHolders.get(position).getBackground().invalidate();
        }
        holder.getWorkoutName().setText(workout.getName());
        holder.getWorkoutInfo().setText(workout.getDescription());
        if(workout.isCompleted()){
            holder.getCompleted().setImageResource(R.drawable.star_fill);
        }else{
            holder.getCompleted().setImageResource(R.drawable.star);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                bNext.setText("Next");
                for(int i=0; i<viewHolders.size(); i++){
                    DataBase.getInstance().getWorkouts().get(i).setChoose(false);
                    viewHolders.get(i).getBackground().setBackgroundColor(0xFF000000);
                    viewHolders.get(i).getWorkoutInfo().setBackgroundColor(0xFF000000);
                    viewHolders.get(i).getBackground().invalidate();
                }
                DataBase.getInstance().getWorkouts().get(position).setChoose(true);
                v.setBackgroundColor(0xFF393939);
                viewHolders.get(position).getWorkoutInfo().setBackgroundColor(0xFF393939);
                v.invalidate();
                if(DataBase.getInstance().getWorkouts().get(position).isCompleted()){
                    bNext.setText("Again");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return DataBase.getInstance().getWorkouts().size();
    }

    public static class WorkoutViewHolder extends RecyclerView.ViewHolder{
        private final ConstraintLayout background;
        private final TextView workoutName;
        private final TextView workoutInfo;
        private final ImageView completed;
        public WorkoutViewHolder(@NonNull View itemView) {
            super(itemView);
            background = itemView.findViewById(R.id.cl_workout_struct);
            workoutName = itemView.findViewById(R.id.tv_workout_name);
            workoutInfo = itemView.findViewById(R.id.tv_workout_info);
            completed = itemView.findViewById(R.id.iv_workout_completed);
        }

        public ConstraintLayout getBackground() {
            return background;
        }

        public TextView getWorkoutName() {
            return workoutName;
        }

        public TextView getWorkoutInfo() {
            return workoutInfo;
        }

        public ImageView getCompleted() {
            return completed;
        }
    }
}
