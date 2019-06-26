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

import java.util.HashMap;

public class TugasWajibActivity extends AppCompatActivity {
    private TextView usernametugaswajib;
    private EditText judultugaswajib, deskripsitugaswajib;
    private Button tugaswajibbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tugas_wajib);
        usernametugaswajib = (TextView)findViewById(R.id.usernametugaswajib);
        judultugaswajib = (EditText)findViewById(R.id.judultugaswajib);
        deskripsitugaswajib = (EditText)findViewById(R.id.deskripsitugaswajib);
        tugaswajibbtn = (Button)findViewById(R.id.tugaswajibbtn);

        usernametugaswajib.setText(Prevalent.currentOnlineUser.getName());

        tugaswajibbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambahtugaswajibklik();
            }
        });
    }

    private void tambahtugaswajibklik() {
        if (TextUtils.isEmpty(judultugaswajib.getText().toString())){
            Toast.makeText(this, "Judul Harap Diisi", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(deskripsitugaswajib.getText().toString())){
            Toast.makeText(this, "Deskripsi Harap Isi", Toast.LENGTH_SHORT).show();
        }
        else{
            tambahtugaswajib();
        }
    }

    private void tambahtugaswajib() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");
        HashMap<String, Object> tambahtugas = new HashMap<>();
        tambahtugas.put("judultugaswajib", judultugaswajib.getText().toString());
        tambahtugas.put("deskripsitugaswajib", deskripsitugaswajib.getText().toString());
        reference.child(Prevalent.currentOnlineUser.getUsername()).child("Tugas Wajib").child(judultugaswajib.getText().toString()).updateChildren(tambahtugas);

        Intent intent = new Intent(TugasWajibActivity.this, AdminCategoryActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Toast.makeText(this, "Tugas Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
        startActivity(intent);
        finish();
    }
}
