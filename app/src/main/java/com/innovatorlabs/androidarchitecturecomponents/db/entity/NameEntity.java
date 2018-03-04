package com.innovatorlabs.androidarchitecturecomponents.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.innovatorlabs.androidarchitecturecomponents.model.Name;

/**
 * This is the model class for the persons name
 */

@Entity(tableName = "names")
public class NameEntity implements Name{

    @PrimaryKey
    private int mId;

    private String mName;

    @Override
    public int getId() {
        return mId;
    }

    @Override
    public String getName() {
        return mName;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public NameEntity(int mId, String mName){
        this.mId = mId;
        this.mName = mName;
    }
}
