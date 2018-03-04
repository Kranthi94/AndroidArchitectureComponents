package com.innovatorlabs.androidarchitecturecomponents.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.innovatorlabs.androidarchitecturecomponents.BaseApplication;
import com.innovatorlabs.androidarchitecturecomponents.db.entity.NameEntity;

import java.util.List;

/**
 * ViewModel for the names
 */

public class NameViewModel extends AndroidViewModel{

    private MediatorLiveData<List<NameEntity>> mNameObservables;

    public NameViewModel(@NonNull Application application) {
        super(application);

        mNameObservables = new MediatorLiveData<>();

        mNameObservables.setValue(null);

        LiveData<List<NameEntity>> names = ((BaseApplication)application).getDataRepository().getNames();

        mNameObservables.addSource(names, new Observer<List<NameEntity>>() {
            @Override
            public void onChanged(@Nullable List<NameEntity> nameEntities) {

                mNameObservables.setValue(nameEntities);
            }
        });
    }

    public LiveData<List<NameEntity>> getNames(){
        return mNameObservables;
    }
}
