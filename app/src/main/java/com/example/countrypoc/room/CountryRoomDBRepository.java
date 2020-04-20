package com.example.countrypoc.room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class CountryRoomDBRepository {
    private CountryDao countryDao;
    LiveData<List<CountryTable>> mAllRecords;

    public CountryRoomDBRepository(Application application){
        RoomDatabase db = RoomDatabase.getDatabase(application);
        countryDao = db.countryDao();
        mAllRecords =countryDao.getAllPosts();
    }

    public LiveData<List<CountryTable>> getAllPosts() {
        return mAllRecords;
    }
    public void insertPosts (ArrayList<CountryTable> modelInformation) {
        new insertAsyncTask(countryDao).execute(modelInformation);
    }

    private static class insertAsyncTask extends AsyncTask<List<CountryTable>, Void, Void> {

        private CountryDao mAsyncTaskDao;

        insertAsyncTask(CountryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final List<CountryTable>... params) {
            mAsyncTaskDao.deleteAll();
            mAsyncTaskDao.insertPosts(params[0]);
            return null;
        }
    }
}