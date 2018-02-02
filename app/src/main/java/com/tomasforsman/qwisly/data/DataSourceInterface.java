package com.tomasforsman.qwisly.data;


import java.util.List;

public interface DataSourceInterface {

    List<ListQuestions> getListOfData();

    ListQuestions createNewListItem();

    void deleteListItem(ListQuestions listQuestions);

    void insertListItem(ListQuestions temporaryListQuestions);

}