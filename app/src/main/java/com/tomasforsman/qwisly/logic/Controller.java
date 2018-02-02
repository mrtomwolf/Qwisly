package com.tomasforsman.qwisly.logic;

import android.util.Log;
import android.view.View;

import com.tomasforsman.qwisly.data.DataSourceInterface;
import com.tomasforsman.qwisly.data.ListQuestions;
import com.tomasforsman.qwisly.view.ViewInterface;

public class Controller {


    private ListQuestions temporaryListItem;
    private int temporaryListItemPosition;
    private ViewInterface view;
    private DataSourceInterface dataSource;

    private static final String TAG = "Controller";


    public Controller(ViewInterface view, DataSourceInterface dataSource){
        this.view = view;
        this.dataSource = dataSource;
        Log.d(TAG, "Controller: called.");

        getListFromDataSource();
    }

    public void onQuestionAnswerClick(ListQuestions selectedItem, View viewRoot){
        Log.d(TAG, "onQuestionClick: " + selectedItem + " : " + viewRoot);

    }

    public void getListFromDataSource(){
        Log.d(TAG, "getListFromDataSource: Started.");
        view.setUpAdapterAndView(
                dataSource.getListOfData()
        );
    }

    public void createNewListItem() {
        Log.d(TAG, "createNewListItem: Started.");
        ListQuestions newItem = dataSource.createNewListItem();

        view.addNewListItemToView(newItem);
        Log.d(TAG, "createNewListItem: end");

    }

}


