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
package com.tomasforsman.qwisly.injection;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import com.tomasforsman.qwisly.data.QuestionDao;
import com.tomasforsman.qwisly.data.QwislyDatabase;
import com.tomasforsman.qwisly.data.QuestionRepository;
import com.tomasforsman.qwisly.viewmodel.CustomViewModelFactory;

@Module
public class RoomModule {

    private final QwislyDatabase database;

    public RoomModule(final Application application) {
        this.database = Room.databaseBuilder(
                application,
                QwislyDatabase.class,
                "Question.db")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    QuestionRepository provideListItemRepository(QuestionDao QuestionDao){
        return new QuestionRepository(QuestionDao);
    }

    @Provides
    @Singleton
    QuestionDao provideListItemDao(QwislyDatabase database){
        return database.listItemDao();
    }

    @Provides
    @Singleton
    QwislyDatabase provideListItemDatabase(Application application){
        return database;
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory provideViewModelFactory(QuestionRepository repository){
        return new CustomViewModelFactory(repository);
    }
}
