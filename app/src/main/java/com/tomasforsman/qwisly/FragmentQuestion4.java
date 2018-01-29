package com.tomasforsman.qwisly;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentQuestion4 extends Fragment{
    private static final String TAG = "FragmentQuestion4";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question4, container, false);

        Log.d(TAG, "onCreateView: started.");





        return view;
    }
}
