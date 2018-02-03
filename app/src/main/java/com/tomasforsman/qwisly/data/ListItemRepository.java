package com.tomasforsman.qwisly.data;

import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

public class ListItemRepository {

    private final ListItemDao ListItemDao;

    @Inject
    public ListItemRepository (ListItemDao ListItemDao){
        this.ListItemDao = ListItemDao;
    }

    public LiveData<ListItem> getListItem(String itemId){
        return ListItemDao.getListItemById(itemId);
    }

    public LiveData<List<ListItem>> getListOfData(){
        return ListItemDao.getListItems();
    }

    public Long createNewListItem(ListItem ListItem){
        return ListItemDao.insertListItem(ListItem);
    }

    public void deleteListItem (ListItem ListItem){
        ListItemDao.deleteListItem(ListItem);
    }


}
