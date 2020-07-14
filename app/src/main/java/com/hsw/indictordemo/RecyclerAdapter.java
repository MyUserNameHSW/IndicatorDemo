package com.hsw.indictordemo;

import android.media.Image;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author heshuai
 * created on: 2020/7/14 3:30 PM
 * description:
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        imageView.setImageResource(R.mipmap.ic_launcher_round);
        return new RecyclerView.ViewHolder(imageView) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
