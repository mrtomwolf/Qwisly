package com.tomasforsman.qwisly;

import android.arch.lifecycle.ViewModel;
import android.support.v4.view.ViewPager;

public class MainViewModel extends ViewModel {
    // Tracks the score
    public int scoreCount = 0;
    public String scoreCountText;
    public int currentView = -1;
    public String currentViewText;
    public boolean[] answered;
    boolean[] arrayAnswers;
    boolean[] arrayUserAnswers;

    public SectionsStagePagerAdapter adapter;

}