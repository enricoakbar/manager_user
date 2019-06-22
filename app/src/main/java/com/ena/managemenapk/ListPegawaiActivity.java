package com.ena.managemenapk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ena.managemenapk.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListPegawaiActivity extends AppCompatActivity {

    private ListView listpegawai;
    private TextView pegawaibtn, namapegawai;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    private Users users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pegawai);

        users = new Users();
        listpegawai = (ListView)findViewById(R.id.listpegawai);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("Users");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.list_pegawai, R.id.namapegawai, list);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren())
                {

                    users = ds.getValue(Users.class);
                    list.add(users.getName().toString());
                }
                listpegawai.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
