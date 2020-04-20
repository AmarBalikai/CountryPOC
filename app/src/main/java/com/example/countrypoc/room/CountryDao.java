package com.example.countrypoc.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CountryDao {
    @Query("SELECT * from country_table")
    LiveData<List<CountryTable>> getAllPosts();

    @Query("DELETE FROM country_table")
    void deleteAll();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPosts(List<CountryTable> resultModel);

}
