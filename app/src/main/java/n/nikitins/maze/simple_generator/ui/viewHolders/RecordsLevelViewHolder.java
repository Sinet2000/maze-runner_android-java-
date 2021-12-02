package n.nikitins.maze.simple_generator.ui.viewHolders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import n.nikitins.maze.simple_generator.R;
import n.nikitins.maze.simple_generator.models.level.Level;

public class RecordsLevelViewHolder extends RecyclerView.ViewHolder {
    private final TextView levelItemView;
    private final TextView timeItemView;
    private final TextView movesItemView;

    private RecordsLevelViewHolder(View itemView) {
        super(itemView);
        levelItemView = itemView.findViewById(R.id.levelTextView);
        timeItemView = itemView.findViewById(R.id.recordsTimeTextView);
        movesItemView = itemView.findViewById(R.id.recordsMovesTextView);
    }

    public void bind(Level level) {
        levelItemView.setText(level.level);
        timeItemView.setText(level.timeTaken);
        movesItemView.setText(level.movesTaken);
    }

    public static RecordsLevelViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.records_list_item, parent, false);

        return new RecordsLevelViewHolder(view);
    }
}
