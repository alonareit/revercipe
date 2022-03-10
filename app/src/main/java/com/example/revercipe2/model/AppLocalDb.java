package com.example.revercipe2.model;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.revercipe2.MyApplication;

@Database(entities = {Ingredients.class}, version = 1)
abstract class AppLocalDbRepository extends RoomDatabase{
    public abstract IngredientsDao ingredientsDao();
}

public class AppLocalDb {
    static public AppLocalDbRepository db =
            Room.databaseBuilder(MyApplication.getContext(),
                    AppLocalDbRepository.class,
                    "dbFileName.db")
                    .fallbackToDestructiveMigration()
                    .build();
}
