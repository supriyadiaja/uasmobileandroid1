package com.example.uas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Pesan extends AppCompatActivity {
    ImageView btnBack;
    EditText edNamaBuku, edKodeBuku;
    Button btnSimpan;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan);

        edNamaBuku = findViewById(R.id.ed_nama_buku);
        edKodeBuku = findViewById(R.id.ed_kode_buku);
        btnSimpan = findViewById(R.id.btn_simpan);
        btnBack = findViewById(R.id.back);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getNamaBuku = edNamaBuku.getText().toString();
                String getKodeBuku = edKodeBuku.getText().toString();

                if (getNamaBuku.isEmpty()){
                    edNamaBuku.setError("Masukkan Nama Buku!");
                } else if (getKodeBuku.isEmpty()){
                    edKodeBuku.setError("Masukkan Kode Buku!");
                }else if (getKodeBuku != "B001" && getKodeBuku != "B002" && getKodeBuku != "B003"){
                    edKodeBuku.setError("Kode tidak ditemukan!");
                }else {
                    database.child("Buku").push().setValue(new ModelBuku(getNamaBuku, getKodeBuku)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(Pesan.this, "Barang Berhasil Di Simpan", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Pesan.this, Keranjang.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Pesan.this, "Gagal Menyimpan Barang!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Pesan.this, MainActivity.class));
            }
        });
    }
}