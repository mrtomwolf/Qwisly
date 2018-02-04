/*
 * *
 *  * Copyright (C) 2018 Tomas Forsman Open Source Project
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.tomasforsman.qwisly.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;


import com.tomasforsman.qwisly.data.User;
import com.tomasforsman.qwisly.data.UserRepository;


public class UserViewModel extends ViewModel {

    private UserRepository repository;
    private String txtTest = "standard";

    UserViewModel(UserRepository repository) {
        this.repository = repository;
    }
    public String getTxt(){
        return txtTest;
    }
    public void setTxt(String s){
        txtTest = s;
    }

    public LiveData<User> getUserById(String userId){
        return repository.getUser(userId);
    }

}

