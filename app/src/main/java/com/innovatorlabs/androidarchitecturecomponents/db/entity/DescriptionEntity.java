package com.innovatorlabs.androidarchitecturecomponents.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.innovatorlabs.androidarchitecturecomponents.model.Description;

/**
 * This is the model class for the persons description
 */

@Entity(tableName = "description")
public class DescriptionEntity implements Description{

    @PrimaryKey
    private int mId;

    private String mName;

    private String mDob;

    private String mNickname;

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public String getDOB() {
        return mDob;
    }

    @Override
    public String getNickname() {
        return mNickname;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public void setDob(String mDob) {
        this.mDob = mDob;
    }

    public void setNickname(String mNickname) {
        this.mNickname = mNickname;
    }

    public DescriptionEntity(int mId, String mName, String mDob, String mNickname){
        this.mId = mId;
        this.mName = mName;
        this.mDob = mDob;
        this.mNickname = mNickname;
    }
}
