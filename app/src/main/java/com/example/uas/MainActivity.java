package com.example.uas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    ImageView logOut;
    EditText edNama, edNpm;
    Button btnListBuku, btnProfile, btnKeranjang, btnPinjam;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("Mahasiswa");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logOut = findViewById(R.id.logout);
        btnKeranjang = findViewById(R.id.btn_keranjang);
        btnProfile = findViewById(R.id.btn_profile);
        btnListBuku = findViewById(R.id.btn_lisbuku);
        btnPinjam = findViewById(R.id.btn_listPeminjam);
        
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                menghapus data pada database
//              database.removeValue();

//              keluar dari sistem
                moveTaskToBack(true);
                finish();
            }
        });

        btnListBuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ListBuku.class));
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, Profile.class));
            }
        });

        btnKeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Keranjang.class));
            }
        });

        btnPinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Pesan.class));
            }
        });
    }
}