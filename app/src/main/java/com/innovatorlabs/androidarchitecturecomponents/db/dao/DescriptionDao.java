package com.innovatorlabs.androidarchitecturecomponents.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.innovatorlabs.androidarchitecturecomponents.db.entity.DescriptionEntity;

import java.util.List;

public interface DescriptionDao {

    @Query("SELECT * FROM description WHERE mId = :id")
    LiveData<DescriptionEntity> loadDescription(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDescription(List<DescriptionEntity> descriptionEntities);
}
