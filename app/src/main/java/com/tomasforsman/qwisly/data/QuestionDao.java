package com.tomasforsman.qwisly.data;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface QuestionDao {


    @Query("SELECT * FROM Question")
    LiveData<List<Question>> getListItems();

    @Query("SELECT * FROM Question WHERE itemId = :itemId")
    LiveData<Question> getListItemById(String itemId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertListItem(Question question);

    @Delete
    void deleteListItem(Question question);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertListItems(Question... Question);




}