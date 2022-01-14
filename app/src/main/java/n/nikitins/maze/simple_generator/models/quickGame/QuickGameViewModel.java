package n.nikitins.maze.simple_generator.models.quickGame;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

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

    public void update(QuickGame quickGame) {
        mQuickGameRepository.update(quickGame);
    }

    public QuickGame getQuickGameByLevel(int level) {
        return mQuickGameRepository.getQuickGameByLevel(level);
    }
}
