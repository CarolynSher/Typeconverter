package android.carolynbicycleshop.typeconverter.Database;

import android.app.Application;
import android.carolynbicycleshop.typeconverter.DAO.ThingDAO;
import android.carolynbicycleshop.typeconverter.Entity.ThingEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Project: Typeconverter
 * Package: android.carolynbicycleshop.typeconverter.Database
 * <p>
 * User: carolyn.sher
 * Date: 10/30/2021
 * Time: 10:04 AM
 * <p>
 * Created with Android Studio
 * To change this template use File | Settings | File Templates.
 */
public class MyRepository {
    private ThingDAO mThingDAO;
    private List<ThingEntity> mAllThings;
    private static int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public MyRepository(Application application) {
        DatabaseBuilder db = DatabaseBuilder.getDatabase(application);
        mThingDAO = db.thingDAO();
    }

    public List<ThingEntity> getAllThings() {
        databaseExecutor.execute(() -> {
            mAllThings = mThingDAO.getAllThings();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllThings;
    }

    public void update(ThingEntity thing) {
        databaseExecutor.execute(() -> {
            mThingDAO.update(thing);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void insert(ThingEntity thing) {
        databaseExecutor.execute(() -> {
            mThingDAO.insert(thing);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(ThingEntity thing) {
        databaseExecutor.execute(() -> {
            mThingDAO.delete(thing);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
