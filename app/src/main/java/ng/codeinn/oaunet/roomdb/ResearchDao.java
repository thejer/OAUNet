package ng.codeinn.oaunet.roomdb;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ResearchDao {
    @Insert
    void bulkInsertResearch(Research... research);

    @Query("SELECT * FROM research")
    List<Research> getAllResearch();

    @Query("SELECT * FROM research WHERE researchId = :id")
    Research getResearchById(int id);

    @Query("DELETE FROM research WHERE researchId = :id")
    int deleteResearchById(int id);

}
