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

import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.tomasforsman.qwisly.data.ListItem;
import com.tomasforsman.qwisly.data.ListItemRepository;

/**
 * Created by R_KAY on 8/11/2017.
 */

public class NewListItemViewModel extends ViewModel {

    private ListItemRepository repository;

    NewListItemViewModel(ListItemRepository repository) {
        this.repository = repository;
    }

    /**
     * Attach our LiveData to the Database
     */
    public void addNewItemToDatabase(ListItem ListItem){
        new AddItemTask().execute(ListItem);
    }

    private class AddItemTask extends AsyncTask<ListItem, Void, Void> {

        @Override
        protected Void doInBackground(ListItem... item) {
            repository.createNewListItem(item[0]);
            return null;
        }
    }
}
