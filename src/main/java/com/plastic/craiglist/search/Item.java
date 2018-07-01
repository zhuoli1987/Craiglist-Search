package com.plastic.craiglist.search;

import java.util.Date;

public class Item {

    private String title;
    private Date date;

    public Item(String title, Date date){
        this.title = title;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
