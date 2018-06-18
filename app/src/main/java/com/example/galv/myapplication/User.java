package com.example.galv.myapplication;

public class User {

    public String name;
    public long point;

    public User(String Fname,long score){
        this.name=Fname;
        this.point=score;
    }


    public String getName() {
        return name;
    }

    public long getPoint() {
        return point;
    }
}
