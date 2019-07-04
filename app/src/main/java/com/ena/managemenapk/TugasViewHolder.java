package com.ena.managemenapk;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
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
    public DatabaseReference reference;

    @SuppressLint("WrongViewCast")
    public TugasViewHolder (@NonNull View tugasView){
        super(tugasView);
        judulTugas = (TextView)tugasView.findViewById(R.id.tugas_wajib_judul);
        desTugas = (TextView)tugasView.findViewById(R.id.tugas_wajib_des);
        ketTugas = (TextView)tugasView.findViewById(R.id.Ket_tugas);
        konfirmasi = (TextView)tugasView.findViewById(R.id.Konfirmasi_Tugas);

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
