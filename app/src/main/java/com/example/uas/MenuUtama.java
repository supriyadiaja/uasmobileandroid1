package com.example.uas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MenuUtama extends AppCompatActivity {
    EditText edNama, edNpm;
    Button btnMenu;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);

        edNama = findViewById(R.id.ed_nama);
        edNpm = findViewById(R.id.ed_nmp);
        btnMenu = findViewById(R.id.btn_menu);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getNamaUser = edNama.getText().toString();
                String getNpmUser = edNpm.getText().toString();

                if (getNamaUser.isEmpty()){
                    edNama.setError("Masukkan Nama");
                }else if (getNpmUser.isEmpty()){
                    edNpm.setError("Masukkan NPM");
                }else {
                    database.child("Mahasiswa").push().setValue(new ModelUser(getNamaUser, getNpmUser)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(MenuUtama.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(MenuUtama.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MenuUtama.this, "Gagal Menyimpan Data", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}