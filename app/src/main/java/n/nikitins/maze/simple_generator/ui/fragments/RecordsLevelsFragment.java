package n.nikitins.maze.simple_generator.ui.fragments;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import n.nikitins.maze.simple_generator.R;
import n.nikitins.maze.simple_generator.models.level.LevelViewModel;
import n.nikitins.maze.simple_generator.ui.adapters.RecordsLevelListAdapter;

public class RecordsLevelsFragment extends Fragment {

    private LevelViewModel levelViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.records_levels_tab, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recordsLevelsList);

        final RecordsLevelListAdapter adapter = new RecordsLevelListAdapter(new RecordsLevelListAdapter.WordDiff());
        recyclerView.setAdapter(adapter);

        levelViewModel = new ViewModelProvider(this).get(LevelViewModel.class);

        levelViewModel.getAllLevels().observe(getViewLifecycleOwner(), adapter::submitList);

        return view;
    }
}