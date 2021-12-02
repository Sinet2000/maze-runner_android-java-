package n.nikitins.maze.simple_generator.models.coordinate;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import n.nikitins.maze.simple_generator.models.level.Level;

@Dao
public interface CoordinateDao {
    @Query("SELECT * FROM coordinate_table")
    LiveData<List<Level>> getAllCoordinates();

    @Query("SELECT * FROM coordinate_table WHERE level LIKE :level")
    LiveData<List<Level>> getCoordinatesByLevel(int level);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Coordinate coordinate);

    @Query("DELETE FROM coordinate_table")
    void deleteAll();
}