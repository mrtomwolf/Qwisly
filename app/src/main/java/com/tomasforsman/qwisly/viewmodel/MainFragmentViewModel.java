package com.tomasforsman.qwisly.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.tomasforsman.qwisly.data.ListItemRepository;

public class MainFragmentViewModel extends ViewModel{
    private ListItemRepository repository;

    MainFragmentViewModel(ListItemRepository repository) {
        this.repository = repository;
    }


}
