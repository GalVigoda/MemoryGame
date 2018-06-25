package com.example.galv.myapplication;

public class User {

    private String FirstName;
    private String stage;
    private String points;

    public User(String fName,String stage, String points){
        setFirstName(fName);
        setStage(stage);
        setPoints(points);
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getPoints() {
        return this.points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
