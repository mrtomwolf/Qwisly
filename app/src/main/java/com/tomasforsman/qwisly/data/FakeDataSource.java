package com.tomasforsman.qwisly.data;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.tomasforsman.qwisly.R;

public class FakeDataSource implements DataSourceInterface{

    private static final int SIZE_OF_COLLECTION = 66;
    private static final String TAG = "FakeDataSource";
    private Random random;



    private final String[] question = {
            "USA have had a president named Chester Arthur",
            "Sweden is famous for their chocolate",
            "The Christian cross is the most recognised symbol in the world",
            "The largest ant colony in the world is more than 10 km from side to side",
    };

    private final String[] answer = {
            "Yes",
            "No",
            "No",
            "Yes",
    };

    private final String[] fact = {
            "He became president in 1881 when James Garfield was murdered.",
            "It's actually Switzerland who has the chocolate. Sweden is a norse country famous for Abba, Vikings and Ikea.",
            "More people recognise the McDonalds symbol than the Christian cross.",
            "In 2000 a new supercolony was found spanning 6000km along the Mediterranean and Atlantic coasts in Southern Europe.",
    };

    private final int[] theId = {
            1,
            2,
            3,
            4
    };


    @Override
    public List<ListQuestions> getListOfData() {
        ArrayList<ListQuestions> listOfData = new ArrayList<>();


        for (int i = 0; i < SIZE_OF_COLLECTION; i++) {

            listOfData.add(
                    createNewListItem()
            );
        }

        return listOfData;
    }

    public FakeDataSource() {
    random = new Random();
    }

    @Override
    public ListQuestions createNewListItem() {
        Log.d(TAG, "createNewListItem(): Started.");

        int q = random.nextInt(4);

        ListQuestions listQuestions = new ListQuestions(
                question[q],
                answer[q],
                fact[q],
                theId[q]
        );


        return listQuestions;
    }

    @Override
    public void deleteListItem(ListQuestions listQuestions) {

    }

    @Override
    public void insertListItem(ListQuestions temporaryListQuestions) {

    }

}
