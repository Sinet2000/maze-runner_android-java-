package n.nikitins.maze.simple_generator.models;

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

    @ColumnInfo(name = "time_taken")
    public String timeTaken;

    @ColumnInfo(name = "moves_taken")
    public int movesTaken;
}
