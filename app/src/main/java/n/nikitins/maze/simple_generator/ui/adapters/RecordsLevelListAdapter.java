package n.nikitins.maze.simple_generator.ui.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import n.nikitins.maze.simple_generator.models.level.Level;
import n.nikitins.maze.simple_generator.ui.viewHolders.RecordsLevelViewHolder;

public class RecordsLevelListAdapter extends ListAdapter<Level, RecordsLevelViewHolder> {

    public RecordsLevelListAdapter(@NonNull DiffUtil.ItemCallback<Level> diffCallback) {
        super(diffCallback);
    }

    @Override
    public RecordsLevelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return RecordsLevelViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(RecordsLevelViewHolder holder, int position) {
        Level current = getItem(position);
        holder.bind(current);
    }

    public static class WordDiff extends DiffUtil.ItemCallback<Level> {

        @Override
        public boolean areItemsTheSame(@NonNull Level oldItem, @NonNull Level newItem) {
            return oldItem.level == newItem.level;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Level oldItem, @NonNull Level newItem) {
            return String.valueOf(oldItem.level).equals(String.valueOf(newItem.level));
        }
    }
}
