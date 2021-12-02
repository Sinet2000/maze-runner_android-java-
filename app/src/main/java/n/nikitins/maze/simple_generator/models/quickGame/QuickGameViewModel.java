package n.nikitins.maze.simple_generator.models.quickGame;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import n.nikitins.maze.simple_generator.models.level.Level;
import n.nikitins.maze.simple_generator.models.level.LevelRepository;

public class QuickGameViewModel extends AndroidViewModel {

    private QuickGameRepository mQuickGameRepository;

    private final LiveData<List<QuickGame>> mAllQuickGames;

    public QuickGameViewModel(Application application) {
        super(application);
        mQuickGameRepository = new QuickGameRepository(application);
        mAllQuickGames = mQuickGameRepository.getAllQuickGames();
    }

    public LiveData<List<QuickGame>> getAllQuickGames() {
        return mAllQuickGames;
    }

    public void insert(QuickGame quickGame) {
        mQuickGameRepository.insert(quickGame);
    }
}
