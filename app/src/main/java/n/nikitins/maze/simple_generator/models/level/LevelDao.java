package n.nikitins.maze.simple_generator.models.level;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import n.nikitins.maze.simple_generator.models.level.Level;

@Dao
public interface LevelDao {
    @Query("SELECT * FROM level_table ORDER BY level ASC")
    LiveData<List<Level>> getAllLevels();

    @Query("SELECT * FROM level_table WHERE level LIKE :level")
    LiveData<List<Level>> getAllRecordsByLevel(int level);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Level level);

    @Query("DELETE FROM level_table")
    void deleteAll();
}
