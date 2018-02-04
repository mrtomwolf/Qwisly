package com.tomasforsman.qwisly.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.tomasforsman.qwisly.data.Question;
import com.tomasforsman.qwisly.data.ListItemRepository;

import java.util.List;

public class ListItemCollectionViewModel extends ViewModel {
    private ListItemRepository repository;

    public ListItemCollectionViewModel(ListItemRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Question>> getListItems(){
        return repository.getListOfData();
    }


    // AyncTask for "proper and easy use of the UI thread.
    // https://developer.android.com/reference/android/os/AsyncTask.html

    public void deleteListItem(Question question){
        DeleteItemTask task = new DeleteItemTask();
        task.execute(question);
    }

    private class DeleteItemTask extends AsyncTask<Question, Void, Void>{

        // Todo: Research RXJava for a better way to do this.

        @Override
        protected Void doInBackground(Question... item) {
            repository.deleteListItem(item[0]);
            return null;
        }
    }

}
