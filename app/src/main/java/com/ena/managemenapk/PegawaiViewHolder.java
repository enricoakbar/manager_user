package com.ena.managemenapk;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ena.managemenapk.Interface.ItemClickListner;

public class PegawaiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView nama, nip, username, password;
    public ItemClickListner listener;

    @SuppressLint("WrongViewCast")
    public PegawaiViewHolder(@NonNull View itemView) {
        super(itemView);
        nama = (TextView)itemView.findViewById(R.id.namapegawai);
        nip = (TextView)itemView.findViewById(R.id.nippegawai);
        username = (TextView)itemView.findViewById(R.id.userpegawai);
        password = (TextView)itemView.findViewById(R.id.passwordpegawai);
    }

    public void setItemClick(ItemClickListner listener)
    {
        this.listener = listener;
    }

    @Override
    public void onClick(View view){
        listener.onClick(view, getAdapterPosition(), false);
    }
}
