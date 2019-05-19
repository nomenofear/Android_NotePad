package com.example.opps.MyNotePad;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SearchView;

public class SearchActivity extends ListActivity implements SearchView.OnQueryTextListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}
