package com.innovatorlabs.androidarchitecturecomponents.db;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.innovatorlabs.androidarchitecturecomponents.AppExecutors;
import com.innovatorlabs.androidarchitecturecomponents.db.dao.DescriptionDao;
import com.innovatorlabs.androidarchitecturecomponents.db.dao.NameDao;
import com.innovatorlabs.androidarchitecturecomponents.db.entity.DescriptionEntity;
import com.innovatorlabs.androidarchitecturecomponents.db.entity.NameEntity;

import java.util.List;

public abstract class AppDatabase extends RoomDatabase {

    private static final String TAG = AppDatabase.class.getSimpleName();

    private static final String DATABASE_NAME = "names_descriptions";

    public abstract NameDao nameDao();

    public abstract DescriptionDao descriptionDao();

    private static AppDatabase mAppDatabase;

    private final MutableLiveData<Boolean> mDatabaseCreated = new MutableLiveData<>();

    public static AppDatabase getInstance(Context context, AppExecutors appExecutors) {

        if (mAppDatabase == null) {

            synchronized (AppDatabase.class) {

                if (mAppDatabase == null) {

                    mAppDatabase = buildDatabase(context.getApplicationContext(), appExecutors);

                    mAppDatabase.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }

        return mAppDatabase;
    }

    private static AppDatabase buildDatabase(final Context context, final AppExecutors appExecutors) {

        return Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).addCallback(new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {

                super.onCreate(db);

                appExecutors.getDiskIO().execute(new Runnable() {
                    @Override
                    public void run() {

                        // Adding delay to stimulate a long running operation
                        addDelay();

                        AppDatabase appDatabase = AppDatabase.getInstance(context, appExecutors);

                        List<NameEntity> nameEntities = DataGenerator.generateNames();

                        List<DescriptionEntity> descriptionEntities = DataGenerator.generateDescriptions();

                        insertData(appDatabase, nameEntities, descriptionEntities);

                        appDatabase.setDatabaseCreated();
                    }
                });
            }
        }).build();
    }

    private static void insertData(final AppDatabase appDatabase, final List<NameEntity> nameEntities,
                                   final List<DescriptionEntity> descriptionEntities){

        appDatabase.runInTransaction(new Runnable() {
            @Override
            public void run() {

                appDatabase.nameDao().insert(nameEntities);

                appDatabase.descriptionDao().insertDescription(descriptionEntities);
            }
        });
    }

    private void updateDatabaseCreated(Context context){

        if(context.getDatabasePath(DATABASE_NAME).exists()){
            setDatabaseCreated();
        }
    }

    private static void addDelay(){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException exception) {
            Log.d(TAG, exception.getMessage());
        }
    }

    private void setDatabaseCreated(){
        mDatabaseCreated.postValue(true);
    }

    public LiveData<Boolean> getDatabaseCreated(){
        return mDatabaseCreated;
    }
}
