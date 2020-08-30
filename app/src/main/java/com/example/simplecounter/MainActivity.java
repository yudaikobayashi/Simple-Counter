package com.example.simplecounter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            ((EditText) findViewById(R.id.title)).setText(savedInstanceState.getString("title"));
            ((EditText) findViewById(R.id.count)).setText(savedInstanceState.getString("count"));
        }

        findViewById(R.id.minus).setOnClickListener(this);
        findViewById(R.id.plus).setOnClickListener(this);
        findViewById(R.id.layout).setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("title", ((EditText) findViewById(R.id.title)).getText().toString());
        outState.putString("count", ((EditText) findViewById(R.id.count)).getText().toString());
    }

    @Override
    public void onClick(View view) {
        View viewFocused = this.getCurrentFocus();
        if (viewFocused != null) {
            viewFocused.clearFocus();
            InputMethodManager manager
                    = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(viewFocused.getWindowToken(), 0);
        }
        if (((TextView) findViewById(R.id.count)).getText().toString().equals("")) {
            ((TextView) findViewById(R.id.count)).setText("0");
        }
        TextView count = findViewById(R.id.count);
        switch (view.getId()) {
            case R.id.minus:
                count.setText(String.valueOf(Integer.parseInt(count.getText().toString()) - 1));
                break;
            case R.id.plus:
            case R.id.layout:
                count.setText(String.valueOf(Integer.parseInt(count.getText().toString()) + 1));
                break;
            default:
                break;
        }
    }
}
