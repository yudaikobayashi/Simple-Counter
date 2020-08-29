package com.example.simplecounter;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.minus).setOnClickListener(this);
        findViewById(R.id.plus).setOnClickListener(this);
        findViewById(R.id.layout).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (((TextView) findViewById(R.id.count)).getText().toString().equals("")) {
            ((TextView) findViewById(R.id.count)).setText("0");
        }
        TextView count = findViewById(R.id.count);
        //getCurrentFocus().clearFocus();
        findViewById(R.id.title).clearFocus();
        findViewById(R.id.count).clearFocus();
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
