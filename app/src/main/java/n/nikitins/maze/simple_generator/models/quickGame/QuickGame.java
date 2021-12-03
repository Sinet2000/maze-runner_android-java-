package n.nikitins.maze.simple_generator.models.quickGame;

import androidx.annotation.NonNull;
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

    public QuickGame(int level, int totalWins, int gamesPlayed, int totalLoses, @NonNull String fastestWin){
        this.level = level;
        this.totalLoses = totalLoses;
        this.totalWins = totalWins;
        this.gamesPlayed = gamesPlayed;
        this.fastestWin = fastestWin;
    }
}