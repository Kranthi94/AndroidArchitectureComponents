package com.innovatorlabs.androidarchitecturecomponents;


import android.arch.lifecycle.LiveData;

import com.innovatorlabs.androidarchitecturecomponents.db.AppDatabase;
import com.innovatorlabs.androidarchitecturecomponents.db.entity.DescriptionEntity;
import com.innovatorlabs.androidarchitecturecomponents.db.entity.NameEntity;

import java.util.List;

public class DataRepository {

    private static DataRepository mDataRepository;

    private AppDatabase mAppDatabase;

    private DataRepository(AppDatabase appDatabase){
        mAppDatabase = appDatabase;
    }

    public static DataRepository getInstance(AppDatabase appDatabase){

        if(mDataRepository == null){

            synchronized (DataRepository.class){

                if(mDataRepository == null){

                    mDataRepository = new DataRepository(appDatabase);
                }
            }
        }

        return mDataRepository;
    }

    public LiveData<List<NameEntity>> getNames(){
        return mAppDatabase.nameDao().loadAllNames();
    }

    public LiveData<DescriptionEntity> getDescriptions(int mId){
        return mAppDatabase.descriptionDao().loadDescription(mId);
    }
}
