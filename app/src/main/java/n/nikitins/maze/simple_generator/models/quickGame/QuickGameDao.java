package n.nikitins.maze.simple_generator.models.quickGame;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QuickGameDao {
    @Query("SELECT * FROM quick_game_table ORDER BY level ASC")
    LiveData<List<QuickGame>> getAllQuickGames();

    @Query("SELECT * FROM quick_game_table WHERE level LIKE :level LIMIT 1")
    QuickGame getRecordByLvl(int level);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(QuickGame quickGame);

    @Update
    void update(QuickGame quickGame);

    @Query("DELETE FROM quick_game_table")
    void deleteAll();
}