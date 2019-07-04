package com.ena.managemenapk;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import com.ena.managemenapk.Interface.ItemClickListner;
import com.ena.managemenapk.Model.Users;
import com.ena.managemenapk.Prevalent.Prevalent;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Tugas_A_Activity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tugas__a_);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(Prevalent.currentOnlineUser.getUsername()).child("Tugas");
        databaseReference.keepSynced(true);

        recyclerView = (RecyclerView) findViewById(R.id.MyTugasRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Users> Options =
                new FirebaseRecyclerOptions.Builder<Users>()
                        .setQuery(databaseReference, Users.class)
                        .build();

        FirebaseRecyclerAdapter<Users, TugasViewHolder> firebaserecyclerAdapter = new FirebaseRecyclerAdapter<Users, TugasViewHolder>(Options) {
            @NonNull
            @Override
            public TugasViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.desain_tugas_a, viewGroup, false);
                TugasViewHolder holder = new TugasViewHolder(v);
                return holder;
            }

            @Override
            protected void onBindViewHolder(@NonNull TugasViewHolder holder, int position, @NonNull Users model) {
                holder.judulTugas.setText(model.getJudultugas());
                holder.desTugas.setText(model.getDeskripsitugas());
                holder.ketTugas.setText(model.getKeterangantugas());
                holder.konfirmasi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Tugas_A_Activity.this, KonfirmasiActivity.class);
                        startActivity(intent);
                    }
                });
            }

        };
        recyclerView.setAdapter(firebaserecyclerAdapter);
        firebaserecyclerAdapter.startListening();
    }
}





