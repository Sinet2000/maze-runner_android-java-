package n.nikitins.maze.simple_generator.models.level;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class LevelViewModel extends AndroidViewModel {

    private LevelRepository mLevelRepository;

    private final LiveData<List<Level>> mAllLevels;

    public LevelViewModel(Application application) {
        super(application);
        mLevelRepository = new LevelRepository(application);
        mAllLevels = mLevelRepository.getAllLevels();
    }

    LiveData<List<Level>> getAllLevels() {
        return mAllLevels;
    }

    void insert(Level level) {
        mLevelRepository.insert(level);
    }
}
