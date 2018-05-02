package ng.codeinn.oaunet.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsertItems(Item... items);



    @Query("SELECT * FROM item WHERE itemType = :type")
    LiveData<List<Item>> getItemsByType(int type);

    @Query("SELECT * FROM Item WHERE itemId = :id")
    LiveData<Item> getItemById(int id);

    @Query("DELETE FROM Item WHERE itemId = :id")
    void deleteItemById(int id);



}
