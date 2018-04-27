package ng.codeinn.oaunet.roomdb;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities={News.class, Event.class, Research.class}, version = 1)
public abstract class OauNetDatabase extends RoomDatabase {

    public abstract NewsDao newsDao();
    public abstract EventDao eventDao();
    public abstract ResearchDao researchDao();

    private static final String DATABASE_NAME = "oaunet";

    private static final Object LOCK = new Object();

    private static volatile OauNetDatabase sInstance;

    public static OauNetDatabase getInstance(Context context){
        if(sInstance == null){
            synchronized (LOCK){
                if (sInstance == null){
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            OauNetDatabase.class, DATABASE_NAME).build();
                }
            }
        }
        return sInstance;
    }

}
