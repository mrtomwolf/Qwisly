package com.tomasforsman.qwisly.main;


import android.arch.lifecycle.Observer;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.Nullable;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;


import com.tomasforsman.qwisly.QwislyApplication;
import com.tomasforsman.qwisly.data.ListItem;

import com.tomasforsman.qwisly.R;
import com.tomasforsman.qwisly.viewmodel.ListItemCollectionViewModel;
import com.tomasforsman.qwisly.viewmodel.NewListItemViewModel;


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
    private Button btnSubmit;
    private NewListItemViewModel newListItemViewModel;


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
        //setRetainInstance(true);


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
            public void onChanged(@Nullable List<ListItem> listItems) {
                if (MainFragment.this.listOfData == null) {
                    setListData(listItems);
                }
            }
        });
        //Set up and subscribe (observe) to the ViewModel
        newListItemViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(NewListItemViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.list_container);
        layoutInflater = getLayoutInflater();

        return v;
    }

    @Override
    public void onResume(){
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



    public void setListData(List<ListItem> listOfData){

        this.listOfData = listOfData;
        if(listOfData.size()<1){

            Log.d("ItemCount: ", "0");

            String[][] questions = {

                    {"USA have had a president named Chester Arthur","Yes","He became president in 1881 when James Garfield was murdered."},
                    {"Sweden is famous for their chocolate", "No","It's actually Switzerland who has the chocolate. Sweden is a norse country famous for Abba, Vikings and Ikea."},
                    {"The Christian cross is the most recognised symbol in the world", "No","More people recognise the McDonalds symbol than the Christian cross."},
                    {"The largest ant colony in the world is more than 10 km from side to side","Yes","In 2000 a new supercolony was found spanning 6000km along the Mediterranean and Atlantic coasts in Southern Europe."}

            };
            for(int n = 0; n < questions.length; n++){
                //ListItem l = new ListItem(n,questions[n][0], questions[n][1], questions[n][2]);
                ListItem l = new ListItem(questions[n][0], questions[n][1], questions[n][2]);
                newListItemViewModel.addNewItemToDatabase(l);

            }


        }else{
            Log.d("ItemCount: ","" + String.valueOf(listOfData.size()));
        }


        //Todo: Look up GridLayoutManager and StaggeredGridLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);


    }

    //-------------------------------------------------------------------------------------//
    //-------------------------------------------------------------------------------------//
    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{

        @Override
        public CustomAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = layoutInflater.inflate(R.layout.fragment_question, parent, false);

            return new CustomViewHolder(v);
        }

        @Override
        public void onBindViewHolder(CustomAdapter.CustomViewHolder holder, int position) {

            ListItem currentItem = listOfData.get(position);
            holder.txtQuestion.setText(
                    currentItem.getQuestion()
            );
            holder.answer = currentItem.getAnswer();

        }

        @Override
        public int getItemCount() {
           return listOfData.size();
        }



        //-------------------------------------------------------------------------------------//
        //-------------------------------------------------------------------------------------//
        class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private RadioButton rbYes;
            private RadioButton rbNo;
            private TextView txtQuestion;
            private ViewGroup container;
            private String answer;


            public CustomViewHolder(View itemView) {
                super(itemView);

                this.rbNo = (RadioButton) itemView.findViewById(R.id.rbNo);
                this.rbYes = (RadioButton) itemView.findViewById(R.id.rbYes);
                this.txtQuestion = (TextView) itemView.findViewById(R.id.txtListItem);

                this.container = (ViewGroup) itemView.findViewById(R.id.root_ListItem);




                this.rbNo.setOnClickListener(this);
                this.rbYes.setOnClickListener(this);

                //this.container.setOnClickListener(this);

            }

            @Override
            public void onClick(View view) {

                this.txtQuestion.setText(answer);

                ListItem ListItem = listOfData.get(
                        this.getAdapterPosition()
                );


            }
        }
    }
}
