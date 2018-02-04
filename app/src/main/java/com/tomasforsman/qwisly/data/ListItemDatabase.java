package com.tomasforsman.qwisly.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Question.class}, version = 9)
public abstract class ListItemDatabase extends RoomDatabase{

    public abstract QuestionDao listItemDao();

}
