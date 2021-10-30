package android.carolynbicycleshop.typeconverter.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

/**
 * Project: Typeconverter
 * Package: android.carolynbicycleshop.typeconverter.Entity
 * <p>
 * User: carolyn.sher
 * Date: 10/30/2021
 * Time: 9:32 AM
 * <p>
 * Created with Android Studio
 * To change this template use File | Settings | File Templates.
 */
@Entity(tableName="thing_table")
public class ThingEntity {
    @PrimaryKey(autoGenerate = true)
    private int thingID;

    private Date thingDate;

    private String thingName;

    public ThingEntity(int thingID, String thingName,Date thingDate) {
        this.thingID = thingID;
        this.thingDate = thingDate;
        this.thingName = thingName;
    }

    public int getThingID() {
        return thingID;
    }

    public void setThingID(int thingID) {
        this.thingID = thingID;
    }

    public Date getThingDate() {
        return thingDate;
    }

    public void setThingDate(Date thingDate) {
        this.thingDate = thingDate;
    }

    public String getThingName() {
        return thingName;
    }

    public void setThingName(String thingName) {
        this.thingName = thingName;
    }

    @Override
    public String toString() {
        return "ThingEntity{" +
                "thingID=" + thingID +
                ", thingDate=" + thingDate +
                ", thingName='" + thingName + '\'' +
                '}';
    }

}