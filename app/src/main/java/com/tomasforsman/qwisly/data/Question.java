package com.tomasforsman.qwisly.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.util.Log;


@Entity
public class Question {


    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int itemId;
    private String question;
    private String answer;
    private String fact;


    //public Question(int itemId, String question, String answer, String fact) {
    public Question(String question, String answer, String fact) {
        //this.itemId = itemId;
        this.question = question;
        this.answer = answer;
        this.fact = fact;
        //Log.d(TAG, "Question()" + question + answer + fact);
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public int getItemId() {
        return itemId;
    }

     public void setItemId(int itemId) {
        this.itemId = itemId;
    }



}
