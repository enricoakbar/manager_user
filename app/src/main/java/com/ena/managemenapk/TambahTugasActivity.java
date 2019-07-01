package com.ena.managemenapk;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ena.managemenapk.Model.Users;
import com.ena.managemenapk.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TambahTugasActivity extends AppCompatActivity {
    private ImageView tugaswajib, tugastambahan;
    private TextView tambahtuhaswajib, tambahtugastambahan;
    private EditText usernamepegawai;
    private ProgressDialog progresBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_tugas);
        tugaswajib = (ImageView)findViewById(R.id.tugaswajib);
        tugastambahan = (ImageView)findViewById(R.id.tugastambahan);
        tambahtugastambahan = (TextView)findViewById(R.id.tambahtugastambahan);
        tambahtuhaswajib = (TextView)findViewById(R.id.tambahtugaswajib);
        usernamepegawai = (EditText)findViewById(R.id.namapegawaitugas);
        progresBar = new ProgressDialog(this);

        tugaswajib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tugaswajib();
            }
        });

        tugastambahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tugastambahan();
            }
        });
    }

    private void tugastambahan() {
        String userName = usernamepegawai.getText().toString();
        if (TextUtils.isEmpty(userName)){
            Toast.makeText(this, "Harap Isi Username Pegawai.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            progresBar.setTitle("Masuk");
            progresBar.setMessage("Tolong tunggu sebentar");
            progresBar.setCanceledOnTouchOutside(false);
            progresBar.show();

            aksestugastambahan(userName);
        }
    }

    private void aksestugastambahan(final String userName) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Users").child(userName).exists()){
                    Users usersData = dataSnapshot.child("Users").child(userName).getValue(Users.class);
                    if (usersData.getUsername().equals(userName)){
                        Toast.makeText(TambahTugasActivity.this, "Tugas Tambahan", Toast.LENGTH_SHORT).show();
                        progresBar.dismiss();
                        Intent intent = new Intent(TambahTugasActivity.this, TugasTambahanActivity.class);
                        Prevalent.currentOnlineUser = usersData;
                        startActivity(intent);
                    }
                }
                else
                {
                    Toast.makeText(TambahTugasActivity.this, "Nama Pegawai Tidak Ditemukan.", Toast.LENGTH_SHORT).show();
                    progresBar.dismiss();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void tugaswajib() {
        String username = usernamepegawai.getText().toString();
        if (TextUtils.isEmpty(username)){
            Toast.makeText(this, "Harap Isi Nama Pegawai.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            progresBar.setTitle("Masuk");
            progresBar.setMessage("Tolong tunggu sebentar");
            progresBar.setCanceledOnTouchOutside(false);
            progresBar.show();

            aksestugaswajib(username);
        }
    }

    private void aksestugaswajib(final String username) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Users").child(username).exists()){
                    Users usersData = dataSnapshot.child("Users").child(username).getValue(Users.class);
                    if (usersData.getUsername().equals(username)){
                        Toast.makeText(TambahTugasActivity.this, "Tugas Wajib", Toast.LENGTH_SHORT).show();
                        progresBar.dismiss();
                        Intent intent = new Intent(TambahTugasActivity.this, TugasWajibActivity.class);
                        Prevalent.currentOnlineUser = usersData;
                        startActivity(intent);
                    }
                }
                else
                {
                    Toast.makeText(TambahTugasActivity.this, "Username Tidak Ditemukan.", Toast.LENGTH_SHORT).show();
                    progresBar.dismiss();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
