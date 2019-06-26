package com.ena.managemenapk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private EditText judultugastambahan, deskripsitugastambahan;
    private Button tugastambahanbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tugas_tambahan);
        usernametugastambahan = (TextView)findViewById(R.id.usernametugastambahan);
        judultugastambahan = (EditText)findViewById(R.id.judultugastambahan);
        deskripsitugastambahan = (EditText)findViewById(R.id.deskripsitugastambahan);
        tugastambahanbtn = (Button)findViewById(R.id.tugastambahanbtn);

        usernametugastambahan.setText(Prevalent.currentOnlineUser.getName());

        tugastambahanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambahtugastambahan();
            }
        });
    }

    private void tambahtugastambahan() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");
        HashMap<String, Object> tambahtugas = new HashMap<>();
        tambahtugas.put("judultugastambahan", judultugastambahan.getText().toString());
        tambahtugas.put("deskripsitugastambahan", deskripsitugastambahan.getText().toString());
        reference.child(Prevalent.currentOnlineUser.getUsername()).child("Tugas Tambahan").child(judultugastambahan.getText().toString()).updateChildren(tambahtugas);

        Intent intent = new Intent(TugasTambahanActivity.this, AdminCategoryActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Toast.makeText(this, "Tugas Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
        startActivity(intent);
        finish();
    }
}
