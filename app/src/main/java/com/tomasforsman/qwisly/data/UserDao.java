package com.tomasforsman.qwisly.data;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDao {


    @Query("SELECT * FROM User")
    LiveData<List<Question>> getUsers();

    @Query("SELECT * FROM User WHERE userId = :userId")
    LiveData<User> getUserById(String userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertUser(User user);

    @Delete
    void deleteUser(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(Question... Question);




}