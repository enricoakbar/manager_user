package com.ena.managemenapk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminCategoryActivity extends AppCompatActivity {
    private ImageView registerpegawai, daftarPegawai, news, task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        registerpegawai = (ImageView)findViewById(R.id.registerpegawai);
        daftarPegawai = (ImageView) findViewById(R.id.daftar_pegawai);
        news = (ImageView) findViewById(R.id.news);
        task = (ImageView)findViewById(R.id.task);

        registerpegawai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        daftarPegawai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, ListPegawaiActivity.class);
                startActivity(intent);
            }
        });


        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("berita", "News");
                startActivity(intent);
            }
        });

        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, TambahTugasActivity.class);
                startActivity(intent);
            }
        });
    }
}
