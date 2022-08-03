package com.example.uas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseAppLifecycleListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.annotation.Documented;
import java.util.Objects;

public class Profile extends AppCompatActivity {
    TextView tvNama, tvNmp;
    ImageView btnBack;

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Mahasiswa");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvNmp = findViewById(R.id.tv_npm);
        tvNama = findViewById(R.id.tv_nama);
        btnBack = findViewById(R.id.back);

//mengambil data dari databse
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ambil: snapshot.getChildren()){
                    String nama = ambil.child("nama").getValue().toString();
                    String npm = ambil.child("npm").getValue().toString();

                    tvNama.setText(nama);
                    tvNmp.setText(npm);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Profile.this, MainActivity.class));
            }
        });
    }
}