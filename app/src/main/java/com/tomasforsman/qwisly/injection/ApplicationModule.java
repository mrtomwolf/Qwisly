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

import dagger.Module;
import dagger.Provides;
import com.tomasforsman.qwisly.QwislyApplication;

@Module
public class ApplicationModule {
    private final QwislyApplication application;
    public ApplicationModule(QwislyApplication application) {
        this.application = application;
    }

    @Provides
    QwislyApplication provideQwislyApplication(){
        return application;
    }

    @Provides
    Application provideApplication(){
        return application;
    }


}
