package com.tomasforsman.qwisly;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class FragmentQuestion1 extends Fragment{
    private static final String TAG = "FragmentQuestion1";

    private boolean firstVisit;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question1, container, false);

        Log.d(TAG, "onCreateView: started.");





        return view;
    }
}
