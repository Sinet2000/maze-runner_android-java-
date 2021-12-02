package n.nikitins.maze.simple_generator.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "quick_game_table")
public class QuickGame {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "level")
    public int level;

    @ColumnInfo(name = "total_wins")
    public int totalWins;

    @ColumnInfo(name = "games_played")
    public int gamesPlayed;

    @ColumnInfo(name = "total_loses")
    public int totalLoses;

    @ColumnInfo(name = "fastest_win")
    public String fastestWin;
}