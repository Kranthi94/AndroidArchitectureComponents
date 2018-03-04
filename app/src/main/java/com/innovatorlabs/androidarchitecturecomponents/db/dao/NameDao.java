package com.innovatorlabs.androidarchitecturecomponents.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.innovatorlabs.androidarchitecturecomponents.db.entity.NameEntity;

import java.util.List;

/**
 * In this class we will write the SQL queries as interface methods
 */

public interface NameDao {

    @Query("SELECT * FROM names")
    LiveData<List<NameEntity>> loadAllNames();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<NameEntity> nameEntities);

    @Query("SELECT * FROM names WHERE mId = :id")
    LiveData<NameEntity> getName(int id);
}
