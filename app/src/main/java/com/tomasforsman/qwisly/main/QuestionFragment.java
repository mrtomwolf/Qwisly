package com.tomasforsman.qwisly.main;

import android.app.Fragment;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.tomasforsman.qwisly.data.Question;
import com.tomasforsman.qwisly.QwislyApplication;
import com.tomasforsman.qwisly.R;
import com.tomasforsman.qwisly.viewmodel.QuestionViewModel;

import javax.inject.Inject;

public class QuestionFragment extends android.support.v4.app.Fragment {

    private static final String EXTRA_ITEM_ID = "EXTRA_ITEM_ID";

    private String itemId;
    private RadioButton rbYes;
    private RadioButton rbNo;
    private TextView txtQuestion;
    private ViewGroup container;
    private String answer;
    private Boolean yes;
    private Boolean no;
    private android.support.v4.app.Fragment mFragment;

    private int tempInt;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private QuestionViewModel questionViewModel;

    public static QuestionFragment newInstance(String itemId) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_ITEM_ID, itemId);

        fragment.setArguments(args);
        return fragment;
    }

    public static QuestionFragment newInstance() {
        return new QuestionFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((QwislyApplication) getActivity().getApplication())
                .getApplicationComponent()
                .inject(this);

        Bundle args = getArguments();

        this.itemId = args.getString(EXTRA_ITEM_ID);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.questionViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(QuestionViewModel.class);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_question, container, false);
        this.rbNo = (RadioButton) v.findViewById(R.id.rbNo);
        this.rbYes = (RadioButton) v.findViewById(R.id.rbYes);
        this.txtQuestion = (TextView) v.findViewById(R.id.txtListItem);

        container = (ViewGroup) v.findViewById(R.id.root_ListItem);

        this.rbNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtQuestion.setText(questionViewModel.getTxt());
            }
        });


        return v;

    }




}
