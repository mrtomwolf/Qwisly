package com.tomasforsman.qwisly.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.Executors;

@Database(entities = {ListItem.class}, version = 7)
public abstract class ListItemDatabase extends RoomDatabase{

    public abstract ListItemDao ListItemDao();

}
