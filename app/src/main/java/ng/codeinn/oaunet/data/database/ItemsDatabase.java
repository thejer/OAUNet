package ng.codeinn.oaunet.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities={Item.class}, version = 1)
public abstract class ItemsDatabase extends RoomDatabase {

    public abstract ItemDao itemDao();

    private static final String DATABASE_NAME = "items";

    private static final Object LOCK = new Object();

    private static volatile ItemsDatabase sInstance;

    public static ItemsDatabase getInstance(Context context){
        if(sInstance == null){
            synchronized (LOCK){
                if (sInstance == null){
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            ItemsDatabase.class, ItemsDatabase.DATABASE_NAME).build();
                }
            }
        }
        return sInstance;
    }

}
