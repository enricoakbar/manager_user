package com.ena.managemenapk;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ena.managemenapk.Interface.ItemClickListner;
import com.ena.managemenapk.Model.Users;
import com.ena.managemenapk.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TugasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView judulTugas, ketTugas, desTugas;
    public TextView konfirmasi;
    public ItemClickListner listener;
    public ImageView plustugas, minustugas;

    @SuppressLint("WrongViewCast")
    public TugasViewHolder (@NonNull View tugasView){
        super(tugasView);
        judulTugas = (TextView)tugasView.findViewById(R.id.tugas_wajib_judul);
        desTugas = (TextView)tugasView.findViewById(R.id.tugas_wajib_des);
        ketTugas = (TextView)tugasView.findViewById(R.id.Ket_tugas);
        konfirmasi = (TextView) tugasView.findViewById(R.id.Konfirmasi_Tugas);
        plustugas = (ImageView)tugasView.findViewById(R.id.plustugas);
        minustugas = (ImageView)tugasView.findViewById(R.id.minustugas);

        plustugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desTugas.setVisibility(View.VISIBLE);
                minustugas.setVisibility(View.VISIBLE);
                plustugas.setVisibility(View.INVISIBLE);
            }
        });
        minustugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desTugas.setVisibility(View.GONE);
                minustugas.setVisibility(View.INVISIBLE);
                plustugas.setVisibility(View.VISIBLE);
            }
        });

    }
    public void setItemClick(ItemClickListner listener)
    {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v, getAdapterPosition(), false);
    }
}
