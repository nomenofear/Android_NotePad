package com.example.opps.MyNotePad;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SearchView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends ListActivity implements SearchView.OnQueryTextListener  {

    private List<OneMemo> memolist = new ArrayList<>();

    //adapter
    MemoAdapter adapter;

    //main ListView
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        SearchView searchview = (SearchView)findViewById(R.id.search_view);
        //为查询文本框注册监听器
        searchview.setOnQueryTextListener(SearchActivity.this);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        //  String selection = "NotesDB Like ? ";
        // String[] selectionArgs = { "%"+s+"%" };
        //   List<Memo> memoes
        Cursor c= DataSupport.findBySQL("select * from memo where title like '%"+s+"%'");

        if(c.moveToFirst()){
            do{
                int tag = c.getInt(c.getColumnIndex("tag"));
                Log.d("s", "onQueryTextChange: "+tag);
                String textDate = c.getString(c.getColumnIndex("textdate"));
                Log.d("textDate", "onQueryTextChange: "+textDate);
                String textTime = c.getString(c.getColumnIndex("texttime"));
                Log.d("textTime", "onQueryTextChange: "+textDate);
                boolean alarm = c.getString(c.getColumnIndex("alarm")).length() > 1 ? true : false;
                String mainText = c.getString(c.getColumnIndex("maintext"));
                String title = c.getString(c.getColumnIndex("title"));
                OneMemo temp = new OneMemo(tag, textDate, textTime, alarm, mainText,title);
                memolist.add(temp);
                //Log.d("MainActivity2", "result: " +result );
            }while(c.moveToNext());
        }
        c.close();

        adapter=new MemoAdapter(SearchActivity.this, R.layout.notes_list, memolist);
        setListAdapter(adapter);
        //  lv=(ListView) findViewById(R.id.note_search_list);
        //   lv.setAdapter(adapter);

        //     lv.setOnItemClickListener(this);
        return false;
    }
}
