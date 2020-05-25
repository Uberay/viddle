package com.example.viddle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnPicListener {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerViewAdapter recyclerViewAdapter;

    int []arr =
            {R.drawable.pic1, R.drawable.pic2,R.drawable.pic3,
            R.drawable.pic4,R.drawable.pic5,R.drawable.pic6,
            R.drawable.pic7,R.drawable.pic8,R.drawable.pic9,
            R.drawable.pic10,R.drawable.pic11,R.drawable.pic12,
            R.drawable.pic13,R.drawable.pic14,R.drawable.pic15,
            R.drawable.pic16,R.drawable.pic17,R.drawable.pic18,
            R.drawable.pic19,R.drawable.pic20,R.drawable.pic21,
            R.drawable.pic22,R.drawable.pic23,R.drawable.pic24,
            R.drawable.pic25,R.drawable.pic26,R.drawable.pic27,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        //the span count determines the number of images displayed per row
        layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(arr,this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setHasFixedSize(true);


    }

    @Override
    public void onPicClick(int position) {
        Log.d("TAG", "Item" + position);
    }
}
