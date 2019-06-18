package com.ena.managemenapk.ViewHolder;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ena.managemenapk.Interface.ItemClickListner;
import com.ena.managemenapk.R;

import org.w3c.dom.Text;

public class NewsViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtNewsName, txtNewsDescription;
    public ImageView imageView;
    public ItemClickListner listner;

    @SuppressLint("WrongViewCast")
    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.news_image);
        txtNewsName = (TextView) itemView.findViewById(R.id.news_name);
        txtNewsDescription = (TextView) itemView.findViewById(R.id.news_description);
    }

    public void setItemClikListner(ItemClickListner listner)
    {
        this.listner = listner;
    }

    @Override
    public void onClick(View v)
    {
        listner.onClick(v, getAdapterPosition(), false);
    }
}
