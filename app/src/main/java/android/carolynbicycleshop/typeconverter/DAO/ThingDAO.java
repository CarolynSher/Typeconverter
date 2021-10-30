package android.carolynbicycleshop.typeconverter.DAO;




import android.carolynbicycleshop.typeconverter.Entity.ThingEntity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ThingDAO {
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    void insert(ThingEntity thing);

    @Delete
    void delete(ThingEntity thing);

    @Update
    void update(ThingEntity thing);

    @Query("Select * From thing_table Order by thingID asc")
    List<ThingEntity> getAllThings();
}

