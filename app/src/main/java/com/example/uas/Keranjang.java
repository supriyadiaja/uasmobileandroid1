package com.example.uas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Keranjang extends AppCompatActivity {
    ImageView btnBack;
    FloatingActionButton btnTambah;
    AdapterMahasiswa adapterMahasiswa;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    ArrayList<ModelBuku> dataBuku;
    RecyclerView tv_tampil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang);

        btnTambah = findViewById(R.id.btn_tambah);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Keranjang.this, Pesan.class));
            }
        });

    tv_tampil = findViewById(R.id.tv_tampil);
    RecyclerView.LayoutManager mlayout = new LinearLayoutManager(this);
    tv_tampil.setLayoutManager(mlayout);
    tv_tampil.setItemAnimator(new DefaultItemAnimator());

    tampilData();

    }

    private void tampilData() {
        database.child("Buku").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataBuku = new ArrayList<>();
                for (DataSnapshot item : snapshot.getChildren()) {
                    ModelBuku buku = item.getValue(ModelBuku.class);
                    buku.setKey(item.getKey());
                    dataBuku.add(buku);
                }
                adapterMahasiswa = new AdapterMahasiswa(dataBuku, Keranjang.this);
                tv_tampil.setAdapter(adapterMahasiswa);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnBack = findViewById(R.id.back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Keranjang.this, MainActivity.class));
            }
        });
    }
}