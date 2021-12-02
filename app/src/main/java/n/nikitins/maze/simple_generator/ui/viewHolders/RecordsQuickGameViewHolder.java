package n.nikitins.maze.simple_generator.ui.viewHolders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import n.nikitins.maze.simple_generator.R;
import n.nikitins.maze.simple_generator.models.quickGame.QuickGame;

public class RecordsQuickGameViewHolder extends RecyclerView.ViewHolder {
    private final TextView levelItemView;
    private final TextView gamesPlayedTextView;
    private final TextView winsTextView;
    private final TextView losesTextView;
    private final TextView fastestWinTextView;

    private RecordsQuickGameViewHolder(View itemView) {
        super(itemView);
        levelItemView = itemView.findViewById(R.id.recordsQuickGamesLevel);
        gamesPlayedTextView = itemView.findViewById(R.id.recordsQuickGamesPlayedTextView);
        winsTextView = itemView.findViewById(R.id.recordsQuickGamesWinTextView);
        losesTextView = itemView.findViewById(R.id.recordsQuickGamesLosesTextView);
        fastestWinTextView = itemView.findViewById(R.id.recordsQuickGamesFastestTextView);
    }

    public void bind(QuickGame quickGame) {
        levelItemView.setText(quickGame.level);
        gamesPlayedTextView.setText(quickGame.gamesPlayed);
        winsTextView.setText(quickGame.totalWins);
        losesTextView.setText(quickGame.totalLoses);
        fastestWinTextView.setText(quickGame.fastestWin);
    }

    public static RecordsQuickGameViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.records_quickgame_listitem, parent, false);

        return new RecordsQuickGameViewHolder(view);
    }
}
