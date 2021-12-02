package n.nikitins.maze.simple_generator.models.coordinate;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "coordinate_table")
public class Coordinate {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "level")
    public int level;

    public String column;

    @ColumnInfo(name = "wall_0")
    public int wall0;

    @ColumnInfo(name = "wall_1")
    public int wall1;

    @ColumnInfo(name = "wall_2")
    public int wall2;

    @ColumnInfo(name = "wall_3")
    public int wall3;
}