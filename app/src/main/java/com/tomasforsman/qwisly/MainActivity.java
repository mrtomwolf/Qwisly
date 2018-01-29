package com.tomasforsman.qwisly;

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

    private String rightAnswer;
    private int scoreCount;
    private String scoreCountText;
    private int quizCount = 1;


    //For fragments
    private int currentView = 0;
    private static final String TAG = "MainActivity";
    public SectionsStagePagerAdapter mSectionsStatePagerAdapter;
    private ViewPager mViewPager;
    public String currentViewText;
    private boolean firstVisit;
    private boolean[] answered;




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
    boolean[] arrayAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started.");

        rbNo = (RadioButton) findViewById(R.id.rbNo);
        rbYes = (RadioButton) findViewById(R.id.rbYes);
        btnNext = (Button) findViewById(R.id.btnNavNext);
        btnPrev = (Button) findViewById(R.id.btnNavPrevious);
        arrayAnswers = new boolean[questionsData.length];
        mSectionsStatePagerAdapter = new SectionsStagePagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        answered = new boolean[questionsData.length];

        rbNo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rbNo.setChecked(true);
                rbYes.setChecked(false);
                setAnswer("No");
            }
        });

        rbYes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rbNo.setChecked(false);
                rbYes.setChecked(true);
                setAnswer("Yes");
            }
        });




        setupViewPager(mViewPager);
        //final Button btnYes = (Button) findViewById(R.id.btnNavNext);
        //final Button btnNo = (Button) findViewById(R.id.btnNavPrevious);



        btnNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(getApplicationContext(), "Next", Toast.LENGTH_SHORT).show();
                if (currentView < 3){
                    currentView +=1;
                    setViewPager(currentView);
                }
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (currentView > 0){
                    currentView -=1;
                    setViewPager(currentView);
                }
                Toast.makeText(getApplicationContext(), "Previous", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAnswer(String answer) {

        if(answer.equals("Yes")){
            //Toast.makeText(getApplicationContext(), "YEAH!", Toast.LENGTH_SHORT).show();
             arrayAnswers[currentView] = true;
             showRights();
        }else{
            //Toast.makeText(getApplicationContext(), "Nah", Toast.LENGTH_SHORT).show();
            arrayAnswers[currentView] = false;
            showRights();
        }
        answered[currentView] = true;
    }

    private void showRights(){
        scoreCount = 0;
        for (int i = 0; i < arrayAnswers.length; i++) {
            if(arrayAnswers[i] && questionsData[i][1].equals("Yes")){
                scoreCount += 1;
            }else if(!arrayAnswers[i] && questionsData[i][1].equals("No")){
                scoreCount += 1;
            }
        }
        scoreCountText = Integer.toString(scoreCount);

        Toast.makeText(getApplicationContext(), scoreCountText, Toast.LENGTH_SHORT).show();
    }

    public void setYesNo(int i){

        if (arrayAnswers[i]){
            rbNo.setChecked(false);
            rbYes.setChecked(true);
        } else if(!answered[i]){
            rbNo.setChecked(false);
            rbYes.setChecked(false);
        } else if (!arrayAnswers[i]){
            rbNo.setChecked(true);
            rbYes.setChecked(false);
        }


    }

    private void setupViewPager(ViewPager viewPager){
        SectionsStagePagerAdapter adapter = mSectionsStatePagerAdapter; //new SectionsStagePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentQuestion1(), "FragmentQuestion1");
        adapter.addFragment(new FragmentQuestion2(), "FragmentQuestion2");
        adapter.addFragment(new FragmentQuestion3(), "FragmentQuestion3");
        adapter.addFragment(new FragmentQuestion4(), "FragmentQuestion4");
        viewPager.setAdapter(adapter);
    }


    public void setViewPager(int fragmentNumber){

        mViewPager.setCurrentItem(fragmentNumber);
        setYesNo(fragmentNumber);
    }



    //questionTitle = (TextView) findViewById(R.id.question);
}
