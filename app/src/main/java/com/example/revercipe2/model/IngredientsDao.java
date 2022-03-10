package com.example.revercipe2.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface IngredientsDao {

        @Query("select * from Ingredients")
        List<Ingredients> getAll();

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertAll(Ingredients... ingredient);

        @Delete
        void delete(Ingredients ingredient);

    }


