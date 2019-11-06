package org.techtown.samplerecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView=findViewById(R.id.recyclerView);

        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        PersonAdapter adapter=new PersonAdapter();

        adapter.addItem(new Person("김민수","010-1000-1000"));
        adapter.addItem(new Person("김하늘","010-2000-2000"));
        adapter.addItem(new Person("홍길동","010-3000-3000"));
        adapter.addItem(new Person("김가빈","010-4000-4000"));
        adapter.addItem(new Person("정찬우","010-5000-5000"));
        adapter.addItem(new Person("송세라","010-6000-6000"));

        recyclerView.setAdapter(adapter);
    }
}
