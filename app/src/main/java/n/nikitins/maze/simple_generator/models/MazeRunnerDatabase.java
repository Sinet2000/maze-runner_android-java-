package n.nikitins.maze.simple_generator.models;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import n.nikitins.maze.simple_generator.models.level.Level;
import n.nikitins.maze.simple_generator.models.level.LevelDao;
import n.nikitins.maze.simple_generator.models.quickGame.QuickGameDao;

@Database(entities = {Level.class}, version = 1, exportSchema = false)
public abstract class MazeRunnerDatabase extends RoomDatabase {

    public abstract LevelDao levelDao();
    public abstract QuickGameDao quickGameDao();

    private static volatile MazeRunnerDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static MazeRunnerDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (MazeRunnerDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        MazeRunnerDatabase.class, "maze_runner_database")
                        .addCallback(sRoomDatabaseCallback)
                        .build();
            }
        }

        return INSTANCE;
    }

    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                LevelDao levelDao = INSTANCE.levelDao();
                QuickGameDao quickGameDao = INSTANCE.quickGameDao();
            });
        }
    };
}
