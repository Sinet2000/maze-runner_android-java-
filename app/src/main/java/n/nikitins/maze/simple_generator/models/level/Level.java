package n.nikitins.maze.simple_generator.models.level;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "level_table")
public class Level {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "level")
    public int level;

    @ColumnInfo(name = "completed")
    public String completed;

    @ColumnInfo(name = "time_taken")
    public String timeTaken;

    @ColumnInfo(name = "moves_taken")
    public int movesTaken;

    @ColumnInfo(name = "columns")
    public int columns;

    @ColumnInfo(name = "dest_row")
    public int destRow;

    @ColumnInfo(name = "dest_column")
    public int destCol;

    @ColumnInfo(name = "max_distance")
    public int maxDistance;
}
