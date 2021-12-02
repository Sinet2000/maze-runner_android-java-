package n.nikitins.maze.simple_generator.models.level;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import n.nikitins.maze.simple_generator.models.MazeRunnerDatabase;

public class LevelRepository {
    private LevelDao mLevelDao;
    private LiveData<List<Level>> mAllLevels;

    LevelRepository(Application application) {
        MazeRunnerDatabase db = MazeRunnerDatabase.getInstance(application);
        mLevelDao = db.levelDao();
        mAllLevels = mLevelDao.getAllLevels();
    }

    LiveData<List<Level>> getAllLevels() {
        return mAllLevels;
    }

    void insert(Level level) {
        MazeRunnerDatabase.databaseWriteExecutor.execute(() -> {
            mLevelDao.insert(level);
        });
    }
}
