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
/*
 *  * Has been changed just enough to suit my own application.
 *  * Tomas Forsman
 */
package com.tomasforsman.qwisly.dependencyinjection;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import com.tomasforsman.qwisly.data.ListItem;
import com.tomasforsman.qwisly.data.ListItemDao;
import com.tomasforsman.qwisly.data.ListItemDatabase;
import com.tomasforsman.qwisly.data.ListItemRepository;
import com.tomasforsman.qwisly.viewmodel.CustomViewModelFactory;

import java.util.concurrent.Executors;

@Module
public class RoomModule {

    private final ListItemDatabase database;

    public RoomModule(final Application application) {
        this.database = Room.databaseBuilder(
                application,
                ListItemDatabase.class,
                "ListItem.db")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    ListItemRepository provideListItemRepository(ListItemDao ListItemDao){
        return new ListItemRepository(ListItemDao);
    }

    @Provides
    @Singleton
    ListItemDao provideListItemDao(ListItemDatabase database){
        return database.ListItemDao();
    }

    @Provides
    @Singleton
    ListItemDatabase provideListItemDatabase(Application application){
        return database;
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory provideViewModelFactory(ListItemRepository repository){
        return new CustomViewModelFactory(repository);
    }
}
