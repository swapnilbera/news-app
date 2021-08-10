package com.example.newsapp;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    LayoutInflater inflater;
    List<News> songs;
    String url;
    private recyclerViewOnClick recyclerViewOnClick;


    public CustomAdapter(Context ctx,recyclerViewOnClick recyclerViewOnClick){
        this.inflater = LayoutInflater.from(ctx);
        this.songs = new ArrayList<News>();
        this.recyclerViewOnClick=recyclerViewOnClick;

    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.text_row_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);

      
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // bind the data
        holder.songTitle.setText(songs.get(position).getHeadline());
        holder.songArtists.setText(songs.get(position).getDate());
        Glide.with(holder.itemView.getContext()).load(songs.get(position).getImage_url()).into(holder.newspic);
        url=songs.get(position).getImage_url();



    }


    @Override
    public int getItemCount() {
        return songs.size();
    }
    public void updateData(List<News> arr) {
        songs.clear();
        songs.addAll(arr);
        notifyDataSetChanged();
    }
    public void removeItem(int position) {
        songs.remove(position);
        notifyItemRemoved(position);
    }


    public  class ViewHolder extends  RecyclerView.ViewHolder{
        TextView songTitle,songArtists;
        ImageView newspic;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            songTitle = itemView.findViewById(R.id.textView);
            songArtists = itemView.findViewById(R.id.textView2);
            newspic=itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewOnClick.onLongItemClick(getAdapterPosition());
                }
            });


        }

    }




}