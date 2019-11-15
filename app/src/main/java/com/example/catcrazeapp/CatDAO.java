package com.example.catcrazeapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.ArrayList;
import java.util.List;

@Dao
public interface CatDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAll(ArrayList<Cat>cats);

    @Query("SELECT * FROM Cat")
    List<Cat>getAllCats();

    @Query("SELECT * FROM cat WHERE id = :id")
    Cat findCatById(String id);
}
