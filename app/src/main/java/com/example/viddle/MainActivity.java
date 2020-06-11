package com.example.viddle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    //this will store the instance of the user data and save for later
    UserData user;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);

        //this is the most motherfucking stupid default ever made
        myToolbar.setContentInsetsAbsolute(0,0);

        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        //the span count determines the number of images displayed per row
        layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(arr,this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setHasFixedSize(true);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  sharedPref.edit();
        editor.putString("key", "sample message");
        editor.commit();
        Log.i("heck",sharedPref.getString("key", "did not exist!"));

        boolean[] sample_puzzle = {true, false, false, true, true, false};
        String[] hints_list = {"Apple", "Banana", "Pineapple", "Corn", "Apricot"};

        JSONArray jsonArray = new JSONArray();
        try {
            jsonArray = new JSONArray(sample_puzzle);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("heck", jsonArray.toString());

        Gson gson = new Gson();
        PuzzleSingle single = new PuzzleSingle("Jake", false, hints_list, sample_puzzle);
        String json = gson.toJson(single);
        Log.i("heck", json);
        PuzzleSingle two = gson.fromJson(json, PuzzleSingle.class);
        Log.i("heck", two.get_answer());

        //the class works!
        this.user = new UserData(sharedPref);
        this.user.save_data(this.getPreferences(Context.MODE_PRIVATE));
        Log.i("heck", "worked!");
    }

    @Override
    public void onPicClick(int position) {
        Log.d("TAG", "Item" + position);
        GuessFragment guessFragment = new GuessFragment(position);

        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        guessFragment.setArguments(bundle);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.frame, guessFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
