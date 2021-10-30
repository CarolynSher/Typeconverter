package android.carolynbicycleshop.typeconverter.Database;

import android.carolynbicycleshop.typeconverter.DAO.ThingDAO;
import android.carolynbicycleshop.typeconverter.Entity.ThingEntity;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

/**
 * Project: Typeconverter
 * Package: android.carolynbicycleshop.typeconverter.Database
 * <p>
 * User: carolyn.sher
 * Date: 10/30/2021
 * Time: 9:31 AM
 * <p>
 * Created with Android Studio
 * To change this template use File | Settings | File Templates.
 */


    @Database(entities = {ThingEntity.class}, version = 1, exportSchema=false)
    @TypeConverters(DateConverter.class)
    public abstract class DatabaseBuilder extends RoomDatabase {
        public abstract ThingDAO thingDAO();

        private static volatile DatabaseBuilder INSTANCE;

        static DatabaseBuilder getDatabase(final Context context) {
            if (INSTANCE == null) {
                synchronized (DatabaseBuilder.class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DatabaseBuilder.class, "MyDatabase.db")
                                .fallbackToDestructiveMigration()
                                .build();
                    }
                }
            }

            return INSTANCE;
        }

    }
