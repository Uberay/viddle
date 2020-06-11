package com.example.viddle;

import java.util.Arrays;
import java.util.HashMap;

//this class just holds the value for the puzzle data itself.
public class PuzzleData {

    //lol just hard code all the puzzle answer data here bc fuck it
    private String[] answer_list = {
            "Between",
            "Similar"
    };

    private String[] puzzle_1 = {
            "Between",
            "Similar",
            "Girls",
            "Hair",
            "Human",
            "Naked",
            "B"
    };

    private String[] puzzle_2 = {
            "Similar",
            "Girls",
            "Hair",
            "Human",
            "Naked",
            "B"
    };
    private HashMap<String, String[]> hash;

    private int count = 27;

    public PuzzleData(){
        this.hash =  new HashMap<String, String[]>();

        put_into_hash(puzzle_1);
        put_into_hash(puzzle_2);
        //insert data here
    }
    private void convert_to_hash(String name, String[] words){
        this.hash.put(name, words);
    }
    private void put_into_hash(String[] lst){
        String word = lst[0];
        String[] array = Arrays.copyOfRange(lst,1, lst.length);
        convert_to_hash(word, array);
    }

    //return a PuzzleSingle
    public PuzzleSingle get_default_puzzle(String key){
        String[] array = this.hash.get(key);
        boolean[] list = new boolean[array.length];
        Arrays.fill(list, 0 , array.length, false);
        return new PuzzleSingle(key, false, array, list);
    }

    public String[] get_answer_list(){
        return this.answer_list;
    }






    //continue filling in the data information
    //maybe it should just be stored in JSON from the start?




}
