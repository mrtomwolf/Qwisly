package com.tomasforsman.qwisly.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.tomasforsman.qwisly.data.QuestionRepository;

public class MainFragmentViewModel extends ViewModel{
    private QuestionRepository repository;

    MainFragmentViewModel(QuestionRepository repository) {
        this.repository = repository;
    }


}
