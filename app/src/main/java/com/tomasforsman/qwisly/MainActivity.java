package com.tomasforsman.qwisly;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView questionTitle;
    private TextView questionLable;
    private Button btnNext;
    private Button btnPrev;
    private RadioButton rbNo;
    private RadioButton rbYes;


    //For fragments
    //private int mViewModel.currentView = 0;
    private static final String TAG = "MainActivity";
    public SectionsStagePagerAdapter mSectionsStatePagerAdapter;

    //private boolean[][] mViewModel.answered;
    public MainViewModel mViewModel;
    public ViewPager mViewPager;





    //Questions
    ArrayList<ArrayList<String>> questionsArray = new ArrayList<>();

    String questionsData[][] = {

            // Question : Right Answer
            {"A yes Question", "Yes"},
            {"A no Question", "No"},
            {"Another yes Question", "Yes"},
            {"Another no Question", "No"}
    };
    //Answers
    

    //Todo: SharedPreferences
    //Todo: Database
    //Todo: Update questions
    //Todo: HighScore
    //Todo: RecycleView
    //Todo: SplashScreen
    //Todo: Categories
    //Todo: Random questions
    //Todo: Timer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);


        Log.d(TAG, "onCreate: Started.");

        rbNo = (RadioButton) findViewById(R.id.rbNo);
        rbYes = (RadioButton) findViewById(R.id.rbYes);
        btnNext = (Button) findViewById(R.id.btnNavNext);
        btnPrev = (Button) findViewById(R.id.btnNavPrevious);

        mSectionsStatePagerAdapter = new SectionsStagePagerAdapter(getSupportFragmentManager());


        if(mViewModel.currentView == -1){ //If this is when we first start the activity.
            mViewModel.currentView = 0;
            mViewPager = (ViewPager) findViewById(R.id.container);
            setupViewPager(mViewPager);

        }else{
            mViewPager = (ViewPager) findViewById(R.id.container);
            mViewPager.setAdapter(mViewModel.adapter);

        }



        setAnswersArray();
        mViewModel.answered = new boolean[questionsData.length];
        mViewModel.arrayUserAnswers = new boolean[questionsData.length];

        rbNo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rbNo.setChecked(true);
                rbYes.setChecked(false);
                mViewModel.answered[mViewModel.currentView] = true;
                mViewModel.arrayUserAnswers[mViewModel.currentView] = false;
                generateScore();
            }
        });

        rbYes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rbNo.setChecked(false);
                rbYes.setChecked(true);
                mViewModel.answered[mViewModel.currentView] = true;
                mViewModel.arrayUserAnswers[mViewModel.currentView] = true;
                generateScore();
            }

        });




        btnNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (mViewModel.currentView < 3){
                    mViewModel.currentView +=1;
                    setViewPager(mViewModel.currentView);
                }
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (mViewModel.currentView > 0){
                    mViewModel.currentView -=1;
                    setViewPager(mViewModel.currentView);
                }
            }
        });
    }

    private void setAnswersArray(){
        mViewModel.arrayAnswers = new boolean[questionsData.length];
        for (int i = 0; i < mViewModel.arrayAnswers.length; i++){
            if(questionsData[i][1].equals("Yes")){
                mViewModel.arrayAnswers[i] = true;
            }
        }

    }

    private void generateScore(){
        mViewModel.scoreCount = 0;
        for (int i = 0; i < mViewModel.arrayAnswers.length; i++){
            if(mViewModel.arrayAnswers[i] == mViewModel.arrayUserAnswers[i] && mViewModel.answered[i]){

                mViewModel.scoreCount++;
            }
        }
        mViewModel.scoreCountText = Integer.toString(mViewModel.scoreCount);

        Toast.makeText(getApplicationContext(), mViewModel.scoreCountText, Toast.LENGTH_SHORT).show();
    }

    public void setYesNo(int i){

        if (mViewModel.answered[i]){
            if(mViewModel.arrayUserAnswers[i]){
                rbNo.setChecked(false);
                rbYes.setChecked(true);
            }else{
                rbNo.setChecked(true);
                rbYes.setChecked(false);
            }
        }else{
            rbNo.setChecked(false);
            rbYes.setChecked(false);
        }
    }

    private void setupViewPager(ViewPager viewPager){
        mViewModel.adapter = mSectionsStatePagerAdapter; //new SectionsStagePagerAdapter(getSupportFragmentManager());
        mViewModel.adapter.addFragment(new FragmentQuestion1(), "FragmentQuestion1");
        mViewModel.adapter.addFragment(new FragmentQuestion2(), "FragmentQuestion2");
        mViewModel.adapter.addFragment(new FragmentQuestion3(), "FragmentQuestion3");
        mViewModel.adapter.addFragment(new FragmentQuestion4(), "FragmentQuestion4");
        viewPager.setAdapter(mViewModel.adapter);
    }


    public void setViewPager(int fragmentNumber){

        mViewPager.setCurrentItem(fragmentNumber);
        setYesNo(fragmentNumber);
    }



    //questionTitle = (TextView) findViewById(R.id.question);
}
