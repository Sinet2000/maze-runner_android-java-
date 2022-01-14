package n.nikitins.maze.simple_generator;

import android.os.Bundle;
import android.view.Window;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTabHost;

import n.nikitins.maze.simple_generator.ui.fragments.RecordsQuickGameFragment;

public class RecordsTabFragmentActivity extends FragmentActivity {

    private FragmentTabHost mTabHost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.records_fragment);

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("QuickGame").setIndicator("Quick Game"),
                RecordsQuickGameFragment.class, null);

    }

}
