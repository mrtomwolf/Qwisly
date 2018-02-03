package com.tomasforsman.qwisly.main;


import android.arch.lifecycle.Observer;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.content.ContextCompat;


import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;


import com.tomasforsman.qwisly.QwislyApplication;
import com.tomasforsman.qwisly.data.ListItem;

import com.tomasforsman.qwisly.submit.SubmitActivity;
import com.tomasforsman.qwisly.R;
import com.tomasforsman.qwisly.viewmodel.ListItemCollectionViewModel;



import java.util.List;

import android.support.v4.app.Fragment;

import javax.inject.Inject;


//Todo: SharedPreferences
//Todo: Room Database
//Todo: Shared Preferences
//Todo: Update ListItems
//Todo: HighScore
//Todo: SplashScreen
//Todo: Categories
//Todo: Random ListItems
//Todo: Timer
//Todo: Themes
//Todo: RestAPI Volley or Retrofit
//Todo: ViewModel

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    private List<ListItem> listOfData;

    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;


    @Inject
    ViewModelProvider.Factory viewModelFactory;

    ListItemCollectionViewModel ListItemCollectionViewModel;

    public MainFragment() {
        // Required empty public constructor
    }


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    /*------------------------------- Lifecycle -------------------------------*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        // Dagger2 Get components from ApplicationComponent.class
        // Components: mainFragment createFragment submitFragment
        // Used to inject the viewModelFactory

        ((QwislyApplication) getActivity().getApplication())
                .getApplicationComponent()
                .inject(this);


    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Using the ViewModel Factory to create the ViewModel
        // Todo: After Udacity course, update to ViewModelProvider.AndroidViewModelFactory
        // Architecture Components 1.1.0 update 22/1 - 18
        // https://developer.android.com/topic/libraries/architecture/release-notes.html
        ListItemCollectionViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ListItemCollectionViewModel.class);

        ListItemCollectionViewModel.getListItems().observe(this, new Observer<List<ListItem>>() {
            @Override
            public void onChanged(@Nullable List<ListItem> ListItems) {
                if (MainFragment.this.listOfData == null) {
                    setListData(ListItems);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.item_question, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.list_container);
        layoutInflater = getLayoutInflater();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void startSubmitActivity(String ListItem, String answer, String fact, int theId) {

    }

    public void setListData(List<ListItem> listOfData){

        this.listOfData = listOfData;


        //Todo: Look up GridLayoutManager and StaggeredGridLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);


    }

    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{

        @Override
        public CustomAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = layoutInflater.inflate(R.layout.item_question, parent, false);
            return new CustomViewHolder(v);
        }

        @Override
        public void onBindViewHolder(CustomAdapter.CustomViewHolder holder, int position) {

            ListItem currentItem = listOfData.get(position);
            holder.txtListItem.setText(
                    currentItem.getListItem()
            );

        }

        @Override
        public int getItemCount() {
            return listOfData.size();
        }

        class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private RadioButton rbYes;
            private RadioButton rbNo;
            private TextView txtListItem;
            private ViewGroup container;


            public CustomViewHolder(View itemView) {
                super(itemView);

                this.rbNo = (RadioButton) itemView.findViewById(R.id.rbNo);
                this.rbYes = (RadioButton) itemView.findViewById(R.id.rbYes);
                this.txtListItem = (TextView) itemView.findViewById(R.id.txtListItem);

                this.container = (ViewGroup) itemView.findViewById(R.id.root_ListItem);



                this.rbNo.setOnClickListener(this);
                this.rbYes.setOnClickListener(this);

                //this.container.setOnClickListener(this);

            }

            @Override
            public void onClick(View view) {

                this.txtListItem.setText("woop");

                ListItem ListItem = listOfData.get(
                        this.getAdapterPosition()
                );


            }
        }
    }
}
