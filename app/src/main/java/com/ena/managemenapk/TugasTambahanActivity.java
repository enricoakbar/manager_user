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

import java.util.HashMap;

public class TugasTambahanActivity extends AppCompatActivity {
    private TextView usernametugastambahan;
    private EditText judultugastambahan, deskripsitugastambahan, pointtugastambahan;
    private Button tugastambahanbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tugas_tambahan);
        usernametugastambahan = (TextView)findViewById(R.id.usernametugastambahan);
        judultugastambahan = (EditText)findViewById(R.id.judultugastambahan);
        deskripsitugastambahan = (EditText)findViewById(R.id.deskripsitugastambahan);
        pointtugastambahan = (EditText)findViewById(R.id.pointtugastambahan);
        tugastambahanbtn = (Button)findViewById(R.id.tugastambahanbtn);

        usernametugastambahan.setText(Prevalent.currentOnlineUser.getName());

        tugastambahanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambahtugastambahanklik();
            }
        });
    }

    private void tambahtugastambahanklik() {
        if (TextUtils.isEmpty(judultugastambahan.getText().toString())){
            Toast.makeText(this, "Judul Harap Diisi", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(deskripsitugastambahan.getText().toString())){
            Toast.makeText(this, "Deskripsi Harap Isi", Toast.LENGTH_SHORT).show();
        }
        else{
            tambahtugas();
        }
    }
    private void tambahtugas(){
        String judul = judultugastambahan.getText().toString();
        String desc = deskripsitugastambahan.getText().toString();
        String poin = pointtugastambahan.getText().toString();
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
            tambahtugastambahan();
        }
    }

    private void tambahtugastambahan() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");
        HashMap<String, Object> tambahtugas = new HashMap<>();
        tambahtugas.put("judultugas", judultugastambahan.getText().toString());
        tambahtugas.put("deskripsitugas", deskripsitugastambahan.getText().toString());
        tambahtugas.put("keterangantugas", "Tugas Tambahan");
        tambahtugas.put("point", pointtugastambahan.getText().toString());
        reference.child(Prevalent.currentOnlineUser.getUsername()).child("Tugas").child(judultugastambahan.getText().toString()).updateChildren(tambahtugas);

        Intent intent = new Intent(TugasTambahanActivity.this, AdminCategoryActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Toast.makeText(this, "Tugas Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
        startActivity(intent);
        finish();
    }
}
