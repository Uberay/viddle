package com.example.viddle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//the adapter is typecasted to RecyclerViewAdapter(this class).MyViewHolder(sub class)
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    int []arr;
    private OnPicListener mOnPicListener;

    public RecyclerViewAdapter(int[] arr, OnPicListener onPicListener) {
        //constructor for the ViewAdapter
        this.arr = arr;
        this.mOnPicListener = onPicListener;
    }

    @NonNull
    @Override
    //this method creates a view holder that holds one inflated view and sticks into ViewGroup -> parent
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Using the layout inflater method
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view, mOnPicListener);
        return myViewHolder;
    }

    //this method happens everytime a new view instance binds to the view holder
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.imageView.setImageResource(arr[position]);
    }
    //simple method that returns the number of views in the viewholder
    @Override
    public int getItemCount() {
        return arr.length;
    }
    //this view holder class holds each each inflated view.
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        OnPicListener onPicListener;


        public MyViewHolder(@NonNull View itemView, OnPicListener onPicListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
            this.onPicListener = onPicListener;
        }

        @Override
        public void onClick(View v) {
            onPicListener.onPicClick(getAdapterPosition());
        }
    }

    public interface OnPicListener{
        void onPicClick (int position);
    }
}
