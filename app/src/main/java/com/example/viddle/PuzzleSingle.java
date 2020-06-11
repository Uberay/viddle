package com.example.viddle;

import java.util.HashMap;
import java.util.ArrayList;


public class PuzzleSingle {
    private String answer;
    private boolean answer_status;
    private String[] hints;
    private HashMap<String,Boolean> hints_status;

    //constructor <- gets called at the start of every application cycle.
    public PuzzleSingle(String answer, boolean answer_status, String[] hints, boolean[] list){
        this.answer = answer;
        this.answer_status = answer_status;
        this.hints = hints;
        this.hints_status = new HashMap<String,Boolean>();
        init_hints_status(list);
    }

    private void init_hints_status(boolean[] lst){
        for (int i = 0; i < this.hints.length; i++){
            this.hints_status.put(this.hints[i], lst[i]);
        }
    }

    public String get_answer(){
        return this.answer;
    }

    public boolean get_status(){
        return this.answer_status;
    }

    public String[] get_hints(){
        return this.hints;
    }

    public String[] get_correct_hints(){
        ArrayList<String> temp = new ArrayList<String>();
        for(int i = 0; i < this.hints.length; i ++){
            if(this.hints_status.get(this.hints[i])){
                temp.add(this.hints[i]);
            }
        }
        return (String[]) temp.toArray();
    }

    private void update_answer(String hint){
        //does NOT check for existence of hint
        this.hints_status.put(hint, true);
    }

    //private to_json();
    //private from_json();



}
