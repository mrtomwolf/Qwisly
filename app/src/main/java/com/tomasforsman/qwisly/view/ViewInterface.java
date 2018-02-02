package com.tomasforsman.qwisly.view;

import java.util.List;
import com.tomasforsman.qwisly.data.ListQuestions;

public interface ViewInterface {

    void startSubmitActivity(String question, String answer, String fact, int theId);

    void setUpAdapterAndView(List<ListQuestions> listOfData);

    void addNewListItemToView(ListQuestions newItem);

    void deleteListItemAt(int position);

    void insertListItemAt(int temporaryListItemPosition, ListQuestions temporaryListItem);


}