package n.nikitins.maze.simple_generator.models.quickGame;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import n.nikitins.maze.simple_generator.models.MazeRunnerDatabase;

// https://github.com/googlecodelabs/android-room-with-a-view

public class QuickGameRepository {
    private QuickGameDao mQuickGameDao;
    private LiveData<List<QuickGame>> mAllQuickGames;

    QuickGameRepository(Application application) {
        MazeRunnerDatabase db = MazeRunnerDatabase.getInstance(application);
        mQuickGameDao = db.quickGameDao();
        mAllQuickGames = mQuickGameDao.getAllQuickGames();
    }

    LiveData<List<QuickGame>> getAllQuickGames() {
        return mAllQuickGames;
    }

    void insert(QuickGame quickGame) {
        MazeRunnerDatabase.databaseWriteExecutor.execute(() -> {
            mQuickGameDao.insert(quickGame);
        });
    }
}
