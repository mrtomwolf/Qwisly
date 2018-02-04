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
import android.arch.lifecycle.ViewModelProvider;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.tomasforsman.qwisly.data.QuestionRepository;


@Singleton
public class CustomViewModelFactory implements ViewModelProvider.Factory {
    private final QuestionRepository repository;

    @Inject
    public CustomViewModelFactory(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(QuestionCollectionViewModel.class)) {
            Log.d("CVM", "LICV");
            return (T) new QuestionCollectionViewModel(repository);

        }else if (modelClass.isAssignableFrom(QuestionViewModel.class)) {
            Log.d("CVM", "LIVM");
            return (T) new QuestionViewModel(repository);

        }else if (modelClass.isAssignableFrom(NewQuestionViewModel.class)) {
                Log.d("CVM", "NLIVM");
                return (T) new NewQuestionViewModel(repository);
        }else if (modelClass.isAssignableFrom(MainFragmentViewModel.class)) {
            Log.d("CVM", "NLIVM");
            return (T) new MainFragmentViewModel(repository);
        }else {
            throw new IllegalArgumentException("ViewModel Not Found");
        }
    }
}
