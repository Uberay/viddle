package com.example.viddle;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

public class UserData {

    //when initializing
    PuzzleSingle[] save;

    //init method
    public UserData(SharedPreferences sharedPref){
        PuzzleData puzzleData = new PuzzleData();
        String[] singles = puzzleData.get_answer_list();
        this.save = new PuzzleSingle[singles.length];


        //reads values from the shared_preferences to populate the array of puzzle singles.
        for (int i = 0; i < singles.length; i++){
            String str = singles[i];
            Gson gson = new Gson();
            String json = sharedPref.getString(str, null);
            if(json == null){
                //means that there is no previous save fill -> use the puzzle_data file.
                Log.v("heck", "NO SAVE");
                this.save[i] = puzzleData.get_default_puzzle(str);
                continue;
            }
            Log.v("heck", "SAVE FOUND");
            PuzzleSingle single = gson.fromJson(json, PuzzleSingle.class);
            this.save[i] = single;
        }

    }

    public void save_data(SharedPreferences pref){
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        for (PuzzleSingle puzzle : this.save){
            editor.putString(puzzle.get_answer(),gson.toJson(puzzle, PuzzleSingle.class) );
        }
        editor.commit();
    }


}