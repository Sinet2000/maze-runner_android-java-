package n.nikitins.maze.simple_generator.services.roomDatabases;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import n.nikitins.maze.simple_generator.models.Level;
import n.nikitins.maze.simple_generator.services.dao.LevelDao;

@Database(entities = {Level.class}, version = 1, exportSchema = false)
abstract class LevelRoomDatabase extends RoomDatabase {

    abstract LevelDao levelDao();

    private static volatile LevelRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final
}
