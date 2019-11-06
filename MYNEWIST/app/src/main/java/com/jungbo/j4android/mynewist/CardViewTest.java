package com.jungbo.j4android.mynewist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class CardViewTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view_test);

        RecyclerView view=(RecyclerView)findViewById(R.id.main_recyclerview);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(new CardViewTestAdapter());
    }
}
