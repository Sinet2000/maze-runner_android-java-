package n.nikitins.maze.simple_generator.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import n.nikitins.maze.simple_generator.R;
import n.nikitins.maze.simple_generator.models.quickGame.QuickGame;

public class RecordsQuickGameListAdapter extends RecyclerView.Adapter<RecordsQuickGameListAdapter.RecordsQuickGameViewHolder> {

    private final LayoutInflater mInflater;
    private List<QuickGame> data;

    public RecordsQuickGameListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    class RecordsQuickGameViewHolder extends RecyclerView.ViewHolder {
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
    }

    @Override
    public RecordsQuickGameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.records_quickgame_listitem, parent, false);
        return new RecordsQuickGameViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecordsQuickGameViewHolder holder, int position) {

        if (data != null) {

            QuickGame quickGame = data.get(position);

            holder.levelItemView.setText(String.format("%d", quickGame.level));
            holder.gamesPlayedTextView.setText(String.format("%d", quickGame.gamesPlayed));
            holder.winsTextView.setText(String.format("%d", quickGame.totalWins));
            holder.losesTextView.setText(String.format("%d", quickGame.totalLoses));
            holder.fastestWinTextView.setText(quickGame.fastestWin);
        }
        else {
            holder.levelItemView.setText("1");
            holder.gamesPlayedTextView.setText("0");
            holder.winsTextView.setText("0");
            holder.losesTextView.setText("0");
            holder.fastestWinTextView.setText("00:00");
        }
    }

    public void setData(List<QuickGame> data) {

        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (data != null)
            return data.size();
        else return 0;
    }
//
//    public static class WordDiff extends DiffUtil.ItemCallback<QuickGame> {
//
//        @Override
//        public boolean areItemsTheSame(@NonNull QuickGame oldItem, @NonNull QuickGame newItem) {
//            return oldItem.level == newItem.level;
//        }
//
//        @Override
//        public boolean areContentsTheSame(@NonNull QuickGame oldItem, @NonNull QuickGame newItem) {
//            return String.valueOf(oldItem.level).equals(String.valueOf(newItem.level));
//        }
//    }
}