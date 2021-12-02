package n.nikitins.maze.simple_generator.models.quickGame;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import n.nikitins.maze.simple_generator.models.quickGame.QuickGame;

@Dao
public interface QuickGameDao {
    @Query("SELECT * FROM quick_game_table ORDER BY level ASC")
    LiveData<List<QuickGame>> getAllQuickGames();

    @Query("SELECT * FROM quick_game_table WHERE level LIKE :level")
    LiveData<List<QuickGame>> getAllRecordsByLevel(int level);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(QuickGame quickGame);

    @Query("DELETE FROM quick_game_table")
    void deleteAll();
}