package ng.codeinn.oaunet.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NewsDao {

    @Insert
    void bulkInsertNews(News... news);

    @Query("SELECT * FROM news")
    List<News> getAllNews();

    @Query("SELECT * FROM news WHERE newsId = :id")
    News getNewsById(int id);

    @Query("DELETE FROM news WHERE newsId = :id")
    int deleteNewsById(int id);


}
