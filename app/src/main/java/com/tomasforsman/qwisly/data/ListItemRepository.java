package com.tomasforsman.qwisly.data;

import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

public class ListItemRepository {

    private final QuestionDao QuestionDao;

    @Inject
    public ListItemRepository (QuestionDao QuestionDao){
        this.QuestionDao = QuestionDao;
    }

    public LiveData<Question> getListItem(String itemId){
        return QuestionDao.getListItemById(itemId);
    }

    public LiveData<List<Question>> getListOfData(){
        return QuestionDao.getListItems();
    }

    public Long createNewListItem(Question question){
        return QuestionDao.insertListItem(question);
    }

    public void deleteListItem (Question question){
        QuestionDao.deleteListItem(question);
    }


}
