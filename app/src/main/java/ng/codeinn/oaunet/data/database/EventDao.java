package ng.codeinn.oaunet.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface EventDao {
    @Insert
    void bulkInsertEvent(Event... event);

    @Query("SELECT * FROM events")
    List<Event> getAllEvent();

    @Query("SELECT * FROM events WHERE eventId = :id")
    Event getEventById(int id);

    @Query("DELETE FROM events WHERE eventId = :id")
    int deleteEventById(int id);

}
