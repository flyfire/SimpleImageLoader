package com.solarexsoft.simpleimageloaderdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridView = (GridView) findViewById(R.id.grid_view);
        Adapter adapter = new Adapter(this.getApplicationContext());
        gridView.setAdapter(adapter);
    }
}
