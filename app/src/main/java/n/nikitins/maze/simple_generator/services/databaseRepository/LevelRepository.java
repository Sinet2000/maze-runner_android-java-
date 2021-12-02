package n.nikitins.maze.simple_generator.services.databaseRepository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import n.nikitins.maze.simple_generator.models.Level;

public class LevelRepository {
    private Level mLevel;
    private LiveData<List<Level>> mAllLevels;

    LevelRepository(Application application) {

    }
}
