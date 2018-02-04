package com.tomasforsman.qwisly.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.RecyclerView;


import com.tomasforsman.qwisly.R;
import com.tomasforsman.qwisly.util.BaseActivity;

public class MainActivity extends BaseActivity {
    private static final String MAIN_FRAG = "MAIN_FRAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        MainFragment fragment = (MainFragment) manager.findFragmentByTag(MAIN_FRAG);

        if (fragment == null) {
            fragment = MainFragment.newInstance();
        }

        addFragmentToActivity(manager,
                fragment,
                R.id.root_main_activity,
                MAIN_FRAG
        );

    }


}
