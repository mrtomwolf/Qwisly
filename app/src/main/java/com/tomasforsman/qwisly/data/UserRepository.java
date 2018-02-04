package com.tomasforsman.qwisly.data;

import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

public class UserRepository {

    private final UserDao UserDao;

    @Inject
    public UserRepository(UserDao UserDao){
        this.UserDao = UserDao;
    }

    public LiveData<User> getUser(String userId){
        return UserDao.getUserById(userId);
    }

    public LiveData<List<Question>> getUserData(){
        return UserDao.getUsers();
    }

    public Long createNewListItem(User user){
        return UserDao.insertUser(user);
    }

    public void deleteUser (User user){
        UserDao.deleteUser(user);
    }


}
