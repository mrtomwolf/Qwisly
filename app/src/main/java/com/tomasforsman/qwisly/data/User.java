package com.tomasforsman.qwisly.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.util.Log;


@Entity
public class User {


    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int userID;
    private String Name;
    private String Score;
    private String Time;


    //public Question(int userId, String question, String answer, String fact) {
    public User(String question, String answer, String fact) {
        //this.userId = userId;
        this.Name = question;
        this.Score = answer;
        this.Time = fact;
        //Log.d(TAG, "Question()" + question + answer + fact);
    }


    @NonNull
    public int getUserID() {
        return userID;
    }

    public void setUserID(@NonNull int userID) {
        this.userID = userID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
