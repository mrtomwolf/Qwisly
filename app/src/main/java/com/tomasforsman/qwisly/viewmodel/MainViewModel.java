package com.tomasforsman.qwisly.viewmodel;

import android.arch.lifecycle.ViewModel;



public class MainViewModel extends ViewModel {
    // Tracks the score
    public int scoreCount = 0;
    public String scoreCountText;
    public int currentView = -1;
    public String currentViewText;
    public boolean[] answered;
    public boolean[] arrayAnswers;
    public boolean[] arrayUserAnswers;



}