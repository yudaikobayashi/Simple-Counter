package com.example.simplecounter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences.Editor editor = this.getPreferences(MODE_PRIVATE).edit();
        if (PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("save_title", false)) {
            String title = this.getPreferences(MODE_PRIVATE).getString("title", "");
            assert title != null;
            if (!title.equals("")) {
                ((EditText) findViewById(R.id.title)).setText(title);
            }
        } else if (savedInstanceState == null) {
            editor.putString("title", "");
        }
        if (PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("save_count", false)) {
            int count = this.getPreferences(MODE_PRIVATE).getInt("count", 0);
            ((EditText) findViewById(R.id.count)).setText(String.valueOf(count));
        } else if (savedInstanceState == null) {
            editor.putInt("count", 0);
        }
        editor.apply();

        findViewById(R.id.minus).setOnClickListener(this);
        findViewById(R.id.plus).setOnClickListener(this);
        findViewById(R.id.layout).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    /*@Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("title", ((EditText) findViewById(R.id.title)).getText().toString());
        outState.putString("count", ((EditText) findViewById(R.id.count)).getText().toString());
    }*/

    @Override
    public void onClick(View view) {
        SharedPreferences.Editor editor = this.getPreferences(MODE_PRIVATE).edit();
        EditText count = findViewById(R.id.count);
        View viewFocused = this.getCurrentFocus();
        if (viewFocused != null) {
            viewFocused.clearFocus();
            InputMethodManager manager
                    = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(viewFocused.getWindowToken(), 0);
        }
        editor.putString("title", String.valueOf(((EditText) findViewById(R.id.title)).getText()));
        editor.apply();
        if (count.getText().toString().equals("")) {
            count.setText("0");
            editor.putInt("count", 0);
            editor.apply();
        }
        switch (view.getId()) {
            case R.id.minus:
                count.setText(String.valueOf(Integer.parseInt(count.getText().toString()) - 1));
                editor.putInt("count", Integer.parseInt(count.getText().toString()));
                editor.apply();
                break;
            case R.id.plus:
                //case R.id.layout:
                count.setText(String.valueOf(Integer.parseInt(count.getText().toString()) + 1));
                editor.putInt("count", Integer.parseInt(count.getText().toString()));
                editor.apply();
                break;
            default:
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences.Editor editor = this.getPreferences(MODE_PRIVATE).edit();
        switch (item.getItemId()) {
            case R.id.reset_title:
                ((EditText) findViewById(R.id.title)).setText("");
                editor.putString("title", "");
                break;
            case R.id.reset_count:
                editor.putInt("count", 0);
                ((EditText) findViewById(R.id.count)).setText("0");
                break;
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            default:
        }
        editor.apply();
        return super.onOptionsItemSelected(item);
    }
}