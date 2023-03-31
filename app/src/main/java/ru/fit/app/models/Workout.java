package ru.fit.app.models;

import java.util.ArrayList;
import java.util.List;

public class Workout {
    private String name;
    private String description;
    private List<Exercise> exercises;
    private boolean completed;
    private boolean choose;

    public Workout(String name, String description) {
        this.name = name;
        this.description = description;
        this.exercises = new ArrayList<>();
        this.completed = false;
        this.choose = false;
    }

    public void addExercise(Exercise exercise){
        exercises.add(exercise);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isChoose() {
        return choose;
    }

    public void setChoose(boolean choose) {
        this.choose = choose;
    }

    public boolean checkExercisesCompleted(){
        boolean allCompleted = false;

        int check = 0;

        for(Exercise exercise : exercises){
            if(exercise.isCompleted()){
                check++;
            }
        }

        if(check == exercises.size()){
            allCompleted = true;
        }

        return allCompleted;
    }

    public void setExerciseDefault(){
        for(Exercise exercise : exercises){
            exercise.setCount(1);
            exercise.setCompleted(false);
        }
    }
}
