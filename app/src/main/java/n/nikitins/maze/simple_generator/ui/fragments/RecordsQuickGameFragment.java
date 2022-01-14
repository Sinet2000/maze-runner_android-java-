package n.nikitins.maze.simple_generator.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import n.nikitins.maze.simple_generator.R;
import n.nikitins.maze.simple_generator.models.quickGame.QuickGameViewModel;
import n.nikitins.maze.simple_generator.ui.adapters.RecordsQuickGameListAdapter;

public class RecordsQuickGameFragment extends Fragment {

    private QuickGameViewModel quickGameViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.records_quickgame_tab, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recordsQuickGamesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final RecordsQuickGameListAdapter adapter = new RecordsQuickGameListAdapter(getContext());
        recyclerView.setAdapter(adapter);

        quickGameViewModel = new ViewModelProvider(this).get(QuickGameViewModel.class);

        quickGameViewModel.getAllQuickGames().observe(getViewLifecycleOwner(), adapter::setData);

        return view;
    }
}