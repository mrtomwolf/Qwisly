package com.tomasforsman.qwisly.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.Executors;

@Database(entities = {ListItem.class}, version = 1)
public abstract class ListItemDatabase extends RoomDatabase{

    private static ListItemDatabase INSTANCE;

    public abstract ListItemDao ListItemDao();

    public synchronized static ListItemDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = buildDatabase(context);
        }
        return INSTANCE;
    }

    private static ListItemDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                ListItemDatabase.class,
                "my-database")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                getInstance(context).ListItemDao().insertListItems(ListItem.populateData());
                                Log.d("ListItemDatabase", "populateData() sent.");
                            }
                        });
                    }
                })
                .build();
    }
}
