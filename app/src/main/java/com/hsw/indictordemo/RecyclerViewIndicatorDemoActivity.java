package com.hsw.indictordemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.hsw.indictordemo.indicator.RecyclerViewIndicator;

public class RecyclerViewIndicatorDemoActivity extends AppCompatActivity {

    private RecyclerView horizontalRecyclerView, verticalRecyclerView;

    private RecyclerViewIndicator verticalIndicator, horizontalIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_indicator);
        horizontalRecyclerView = findViewById(R.id.horizontal_recycler_view);
        verticalRecyclerView = findViewById(R.id.vertical_recycler_view);
        horizontalIndicator = findViewById(R.id.horizontal_indicator);
        verticalIndicator = findViewById(R.id.vertical_indicator);
        horizontalRecyclerView.setAdapter(new RecyclerAdapter());
        verticalRecyclerView.setAdapter(new RecyclerAdapter());


        horizontalIndicator.setWithRecyclerView(horizontalRecyclerView, RecyclerView.HORIZONTAL);
        verticalIndicator.setWithRecyclerView(verticalRecyclerView, RecyclerView.VERTICAL);

    }
}
