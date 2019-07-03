package com.ena.managemenapk;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ena.managemenapk.Prevalent.Prevalent;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class KonfirmasiActivity extends AppCompatActivity {

    private EditText desKonfir;
    private ImageView imageKonfir;
    private Button konfirBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi);

        konfirBtn = (Button) findViewById(R.id.button_Konfirmasi);
        desKonfir = (TextView) findViewById(R.id.des_txt);
        imageKonfir = (ImageView) findViewById(R.id.gambar_txt);

        konfirBtn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Check();
            }
        }));

    }
    private void Check (){
        if (TextUtils.isEmpty((konfirBtn.getText().toString()))){
            Toast.makeText(this,"isi deskripsi yang ingin kamu tambahkan terkait tugas yang diberikan",Toast.LENGTH_SHORT).show();
        }
        else{
            Konfirmasi();
        }
    }

    private void Konfirmasi() {
        final String saveCurrentDate, saveCurrentTime;

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate =currentDate.format((calForDate.getTime()));

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentDate.format(calForDate.getTime());

        final DatabaseReference konfRef = FirebaseDatabase.getInstance().getReference().child("Users")
                .child(Prevalent.currentOnlineUser.getUsername()).child("Tugas");

        HashMap<String, Object> ordersMap = new
    }
}
