package com.ena.managemenapk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ena.managemenapk.Prevalent.Prevalent;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class TugasWajibActivity extends AppCompatActivity {
    private TextView usernametugaswajib;
    private EditText judultugaswajib, deskripsitugaswajib, pointtugaswajib;
    private Button tugaswajibbtn;
    private String saveCurrentDate, saveCurrentTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tugas_wajib);
        usernametugaswajib = (TextView)findViewById(R.id.usernametugaswajib);
        judultugaswajib = (EditText)findViewById(R.id.judultugaswajib);
        deskripsitugaswajib = (EditText)findViewById(R.id.deskripsitugaswajib);
        pointtugaswajib = (EditText) findViewById(R.id.pointtugaswajib);
        tugaswajibbtn = (Button)findViewById(R.id.tugaswajibbtn);

        usernametugaswajib.setText(Prevalent.currentOnlineUser.getName());

        tugaswajibbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambahtugaswajibklik();
            }
        });
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());
    }

    private void tambahtugaswajibklik() {
        if (TextUtils.isEmpty(judultugaswajib.getText().toString())){
            Toast.makeText(this, "Judul Harap Diisi", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(deskripsitugaswajib.getText().toString())){
            Toast.makeText(this, "Deskripsi Harap Isi", Toast.LENGTH_SHORT).show();
        }
        else{
            tambahtugas();
        }
    }
    private void tambahtugas(){
        String judul = judultugaswajib.getText().toString();
        String desc = deskripsitugaswajib.getText().toString();
        String poin = pointtugaswajib.getText().toString();
        if (TextUtils.isEmpty(judul)){
            Toast.makeText(this, "Judul Harap Isi", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(desc)){
            Toast.makeText(this, "Deskripsi Harap Isi", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(poin)){
            Toast.makeText(this, "Poin Harap Isi", Toast.LENGTH_SHORT).show();
        }
        else{
            tambahtugaswajib();
        }
    }

    private void tambahtugaswajib() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");
        HashMap<String, Object> tambahtugas = new HashMap<>();
        tambahtugas.put("judultugas", judultugaswajib.getText().toString());
        tambahtugas.put("deskripsitugas", deskripsitugaswajib.getText().toString());
        tambahtugas.put("point", pointtugaswajib.getText().toString());
        tambahtugas.put("keterangantugas", "Tugas Wajib");
        tambahtugas.put("date", saveCurrentDate);
        tambahtugas.put("time", saveCurrentTime);
        reference.child(Prevalent.currentOnlineUser.getUsername()).child("Tugas").child(judultugaswajib.getText().toString()).updateChildren(tambahtugas);

        Intent intent = new Intent(TugasWajibActivity.this, AdminCategoryActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Toast.makeText(this, "Tugas Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
        startActivity(intent);
        finish();
    }
}
