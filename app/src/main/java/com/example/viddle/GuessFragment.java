package com.example.viddle;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GuessFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GuessFragment extends Fragment {

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

    private int number;

    boolean inflated;

    public GuessFragment(int num) {
        this.number = num;
    }


    // Returns a single instance of the fragment
    public static GuessFragment newInstance(int num) {
        GuessFragment fragment = new GuessFragment(num);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle = this.getArguments();
        int position = bundle.getInt("position");
        View view = inflater.inflate(R.layout.fragment_guess, container, false);

        TextView textView_center = getActivity().findViewById(R.id.toolbar_center);
        textView_center.setText("" + (position + 1));

        ImageView imageView = view.findViewById(R.id.fragment_image);
        imageView.setImageResource(arr[position]);

        ImageView back_button = getActivity().findViewById(R.id.toolbar_left);

        back_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                if(inflated) {
                    getActivity().onBackPressed();
                }
            }

        });



        inflated = true;
        return view;
    }

    @Override
    public void onDestroy() {
        inflated = false;
        TextView textView = getActivity().findViewById(R.id.toolbar_center);
        textView.setText("Classic");
        //really dumb way of getting rid of the keyboard
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),0);
        super.onDestroy();
    }
}
