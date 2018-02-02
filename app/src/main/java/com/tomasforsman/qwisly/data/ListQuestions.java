package com.tomasforsman.qwisly.data;

import android.util.Log;

public class ListQuestions {

    private String question;
    private String answer;
    private String fact;
    private int theId;
    private static final String TAG = "ListQuestions";

    public ListQuestions(String question, String answer, String fact, int theId) {
        this.question = question;
        this.answer = answer;
        this.fact = fact;
        this.theId = theId;
        Log.d(TAG, "ListQuestions()" + question + answer + fact);
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

    public int getTheId() {
        return theId;
    }

    public void setTheId(int id) {
        this.theId = theId;
    }
}
