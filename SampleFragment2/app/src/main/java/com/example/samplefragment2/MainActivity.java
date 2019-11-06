package com.example.samplefragment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ListFragment.ImageSelectionCallback{
    ListFragment listFragment;
    ViewrFragment viewrFragment;

    int [] images={R.drawable.dream01,R.drawable.dream02,R.drawable.dream03};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager manager=getSupportFragmentManager();
        listFragment=(ListFragment)manager.findFragmentById(R.id.listFragment);
        viewrFragment=(ViewrFragment)manager.findFragmentById(R.id.viewerFragment);
    }

    public void onImageSelected(int position){
        viewrFragment.setImage(images[position]);
    }
}
