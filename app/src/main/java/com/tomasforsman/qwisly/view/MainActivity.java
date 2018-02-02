package com.tomasforsman.qwisly.view;

import android.app.Activity;
import android.app.ListActivity;
import android.arch.lifecycle.ViewModelProviders;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;


import com.tomasforsman.qwisly.data.FakeDataSource;
import com.tomasforsman.qwisly.logic.Controller;
import com.tomasforsman.qwisly.viewmodel.MainViewModel;
import com.tomasforsman.qwisly.R;
import com.tomasforsman.qwisly.data.ListQuestions;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewInterface, View.OnClickListener {



    //For fragments
    //private int mViewModel.currentView = 0;
    private static final String TAG = "MainActivity";
    //public SectionsStagePagerAdapter mSectionsStatePagerAdapter;

    //private boolean[][] mViewModel.answered;
    public MainViewModel mViewModel;
    //public ViewPager mViewPager;


    //Questions
    ArrayList<ArrayList<String>> questionsArray = new ArrayList<>();

    //Todo: SharedPreferences
    //Todo: Room Database
    //Todo: Shared Preferences
    //Todo: Update questions
    //Todo: HighScore
    //Todo: SplashScreen
    //Todo: Categories
    //Todo: Random questions
    //Todo: Timer
    //Todo: Themes
    //Todo: RestAPI Volley or Retrofit
    //Todo: ViewModel


    private static final String EXTRA_QUESTION="EXTRA_QUESTION";
    private static final String EXTRA_ANSWER="EXTRA_ANSWER";
    private static final String EXTRA_FACT="EXTRA_FACT";
    private static final int EXTRA_THEID=2;

    private List<ListQuestions> listOfData;

    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;


    private Controller controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        recyclerView = (RecyclerView) findViewById(R.id.list_container);
        layoutInflater = getLayoutInflater();

        controller = new Controller(this, new FakeDataSource());


    }

    @Override
    public void startSubmitActivity(String question, String answer, String fact, int theId) {

    }

    public void setUpAdapterAndView(List<ListQuestions> listOfData){

        this.listOfData = listOfData;
        Log.d(TAG, "setUpAdapterAndView: Started.");

        //Todo: Look up GridLayoutManager and StaggeredGridLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(
                recyclerView.getContext(),
                layoutManager.getOrientation()
        );

        itemDecoration.setDrawable(
                ContextCompat.getDrawable(
                        MainActivity.this,
                        R.drawable.divider_white
                )
        );

       /*
        recyclerView.addItemDecoration(
                itemDecoration
        );

        Log.d(TAG, "addItemDecoration: done.");
        */
        //ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
        //itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    @Override
    public void addNewListItemToView(ListQuestions newItem){

        listOfData.add(newItem);

        int endOfList = listOfData.size() -1;

        adapter.notifyItemInserted(endOfList);

        recyclerView.smoothScrollToPosition(endOfList);
        Log.d(TAG, "addNewListItemToView: Done.");

    }

    // Todo: Switch a question option.
    @Override
    public void deleteListItemAt(int position) {
        listOfData.remove(position);

        adapter.notifyItemRemoved(position);

    }

    @Override
    public void insertListItemAt(int position, ListQuestions listQuestions) {
        listOfData.add(position, listQuestions);

        adapter.notifyItemInserted(position);


    }

    @Override
    public void onClick(View view){


    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{

        @Override
        public CustomAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = layoutInflater.inflate(R.layout.item_question, parent, false);
            return new CustomViewHolder(v);
        }

        @Override
        public void onBindViewHolder(CustomAdapter.CustomViewHolder holder, int position) {


            Log.d(TAG, "onBindViewHolder: Started.");
            ListQuestions currentItem = listOfData.get(position);
            Log.d(TAG, "onBindViewHolder: Item: " + currentItem);
            holder.txtQuestion.setText(
                    currentItem.getQuestion()
            );


            //holder.loading.setVisibility(View.INVISIBLE);

        }

        @Override
        public int getItemCount() {
            return listOfData.size();
        }

        class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private RadioButton rbYes;
            private RadioButton rbNo;
            private TextView txtQuestion;
            private ViewGroup container;
            private static final String TAG = "CustomViewHolder";

            public CustomViewHolder(View itemView) {
                super(itemView);

                this.rbNo = (RadioButton) itemView.findViewById(R.id.rbNo);
                this.rbYes = (RadioButton) itemView.findViewById(R.id.rbYes);
                this.txtQuestion = (TextView) itemView.findViewById(R.id.txtQuestion);
                this.container = (ViewGroup) itemView.findViewById(R.id.root_question);
                Log.d(TAG, "CustomViewHolder: Started.");


                this.rbNo.setOnClickListener(this);
                this.rbYes.setOnClickListener(this);

                //this.container.setOnClickListener(this);

            }

            @Override
            public void onClick(View view) {
            //int v = view.getId();
               // Log.d(TAG, "onClick: " + v);

                this.txtQuestion.setText("woop");

                ListQuestions listQuestions = listOfData.get(
                        this.getAdapterPosition()
                );
                Log.d(TAG, "getAdapterPosition(): " + this.getAdapterPosition());
                controller.onQuestionAnswerClick(
                        listQuestions,
                        view
                );
            }
        }
    }
}
