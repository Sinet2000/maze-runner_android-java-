package n.nikitins.maze.simple_generator.ui.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import n.nikitins.maze.simple_generator.models.level.Level;
import n.nikitins.maze.simple_generator.models.quickGame.QuickGame;
import n.nikitins.maze.simple_generator.ui.viewHolders.RecordsLevelViewHolder;
import n.nikitins.maze.simple_generator.ui.viewHolders.RecordsQuickGameViewHolder;

public class RecordsQuickGameListAdapter extends ListAdapter<QuickGame, RecordsQuickGameViewHolder> {

    public RecordsQuickGameListAdapter(@NonNull DiffUtil.ItemCallback<QuickGame> diffCallback) {
        super(diffCallback);
    }

    @Override
    public RecordsQuickGameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return RecordsQuickGameViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(RecordsQuickGameViewHolder holder, int position) {
        QuickGame current = getItem(position);
        holder.bind(current);
    }

    public static class WordDiff extends DiffUtil.ItemCallback<QuickGame> {

        @Override
        public boolean areItemsTheSame(@NonNull QuickGame oldItem, @NonNull QuickGame newItem) {
            return oldItem.level == newItem.level;
        }

        @Override
        public boolean areContentsTheSame(@NonNull QuickGame oldItem, @NonNull QuickGame newItem) {
            return String.valueOf(oldItem.level).equals(String.valueOf(newItem.level));
        }
    }
}