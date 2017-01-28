package com.zionroad.app.golfscoreapp;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
// TODO: commented well!
public class MainActivity extends ListActivity {
    // --- Initialize the SharedPreferences ---
    private static final String PRFES_FILE = "com.zionroad.app.golfscoreapp.preferences";
    private static final String KEY_STROKECOUNT = "KEY_STROKECOUNT";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private Hole[] mHoles = new Hole[18];
    private ListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferences = getSharedPreferences(PRFES_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        // TODO: Find out: Is it really necessary here?
        mEditor.apply();



        // Initialize holes
        Integer stroke = 0;
        for(int i=0; i < mHoles.length; i++) {
            stroke = mSharedPreferences.getInt(KEY_STROKECOUNT + i, 0);
            mHoles[i] = new Hole(String.format("Hole %s:", (i+1)), stroke);
        }


        mListAdapter = new ListAdapter(this, mHoles);
        setListAdapter(mListAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        for(int i = 0; i < mHoles.length; i++) {
            mEditor.putInt(KEY_STROKECOUNT + i, mHoles[i].getStrokeCount());
        }
        mEditor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_clear_strokes) {
            mEditor.clear();
            mEditor.apply();

            for (Hole hole : mHoles) {
                hole.setStrokeCount(0);
            }
            mListAdapter.notifyDataSetChanged();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
