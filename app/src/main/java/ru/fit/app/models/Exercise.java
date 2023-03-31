package ru.fit.app.models;

public class Exercise {
    private String name;
    private String information;
    private String description;
    private int count;
    private boolean completed;
    private boolean choose;

    private int imageId;

    public Exercise(String name, String information, int imageId) {
        this.name = name;
        this.information = information;
        this.count = 1;
        this.completed = false;
        this.choose = false;
        this.imageId = imageId;
        this.description = "Some Description";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public boolean setNextCount(){
        count++;
        if(count > 4){
            setCompleted(true);
            return false;
        }
        return true;
    }
}
