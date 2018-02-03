package com.tomasforsman.qwisly.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.util.Log;

@Entity
public class ListItem {

    @PrimaryKey()
    private int itemId;
    private String ListItem;
    private String answer;
    private String fact;

    private static final String TAG = "ListItem";

    public ListItem(int itemId, String ListItem, String answer, String fact) {
        this.itemId = itemId;
        this.ListItem = ListItem;
        this.answer = answer;
        this.fact = fact;
        Log.d(TAG, "ListItem()" + ListItem + answer + fact);
    }

    public String getListItem() {
        return ListItem;
    }

    public void setListItem(String ListItem) {
        this.ListItem = ListItem;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public static ListItem[] populateData() {
        Log.d("ListItem","populateData() created");
        return new ListItem[] {
                new ListItem(1,"image1.jpg", "title1", "text1"),
                new ListItem(2,"image2.jpg", "title2", "text2"),
                new ListItem(3,"image3.jpg", "title3", "text3"),
                new ListItem(4,"image4.jpg", "title4", "text4"),
                new ListItem(5,"image5.jpg", "title5", "text5")
        };
    }
}
