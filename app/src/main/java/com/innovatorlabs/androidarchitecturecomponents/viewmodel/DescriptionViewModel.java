package com.innovatorlabs.androidarchitecturecomponents.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.innovatorlabs.androidarchitecturecomponents.BaseApplication;
import com.innovatorlabs.androidarchitecturecomponents.db.entity.DescriptionEntity;

/**
 * ViewModel class for Descriptions
 */

public class DescriptionViewModel extends AndroidViewModel {

    private MediatorLiveData<DescriptionEntity> mDescriptionObservables;

    public DescriptionViewModel(@NonNull Application application, int mId) {

        super(application);

        mDescriptionObservables = new MediatorLiveData<>();

        mDescriptionObservables.setValue(null);

        LiveData<DescriptionEntity> description = ((BaseApplication)application).getDataRepository().getDescriptions(mId);

        mDescriptionObservables.addSource(description, new Observer<DescriptionEntity>() {
            @Override
            public void onChanged(@Nullable DescriptionEntity descriptionEntity) {

                mDescriptionObservables.setValue(descriptionEntity);
            }
        });
    }
}
