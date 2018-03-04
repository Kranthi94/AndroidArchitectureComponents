package com.innovatorlabs.androidarchitecturecomponents;

import android.app.Application;

import com.innovatorlabs.androidarchitecturecomponents.db.AppDatabase;

/**
 * This is the application class which is the first one to load when process for this app starts.
 *
 * Here we are initiating the executor and databases which are needed for the application
 */

public class BaseApplication extends Application {

    private AppExecutors mAppExecutors;

    @Override
    public void onCreate() {

        super.onCreate();

        mAppExecutors = new AppExecutors();
    }

    private AppDatabase getDatabase(){
        return AppDatabase.getInstance(this, mAppExecutors);
    }

    public DataRepository getDataRepository(){
        return DataRepository.getInstance(getDatabase());
    }
}
