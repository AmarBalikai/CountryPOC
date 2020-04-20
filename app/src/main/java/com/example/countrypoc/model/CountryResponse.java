package com.example.countrypoc.model;



import com.example.countrypoc.room.CountryTable;

import java.util.ArrayList;

public class CountryResponse
{

    private String title ="";
    private ArrayList<CountryTable> rows;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<CountryTable> getRows() {
        return rows;
    }

    public void setRows(ArrayList<CountryTable> rows) {
        this.rows = rows;
    }
}
