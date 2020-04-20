package com.example.countrypoc.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;


@Database(entities = {CountryTable.class}, version = 1)
public abstract class RoomDatabase extends androidx.room.RoomDatabase {
    public abstract CountryDao countryDao();

    private static RoomDatabase INSTANCE;


    static RoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDatabase.class, "country_database")
                            //.addCallback(sRoomDatabaseCallback)
                            .build();

                }
            }
        }
        return INSTANCE;
    }

    /*private static Callback sRoomDatabaseCallback =
            new Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };
    public static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final CountryDao mDao;

        PopulateDbAsync(RoomDatabase db) {
            mDao = db.countryDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            return null;
        }
    }*/
}
