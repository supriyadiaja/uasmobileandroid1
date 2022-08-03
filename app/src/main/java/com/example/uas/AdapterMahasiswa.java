package com.example.uas;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdapterMahasiswa extends RecyclerView.Adapter <AdapterMahasiswa.MyViewholder>{
    private List<ModelBuku> mlist;
    private Activity activity;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public AdapterMahasiswa(List<ModelBuku>mlist, Activity activity){
        this.mlist = mlist;
        this.activity = activity;
    }
    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem = inflater.inflate(R.layout.layout_item, parent, false);
        return new MyViewholder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {

        final ModelBuku data = mlist.get(position);
        holder.tv_buku.setText("Buku : "+ data.getBuku());
        holder.tv_kode.setText("Kode : "+ data.getKode());
        holder.btn_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        database.child("Buku").child(data.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(activity, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(activity, "Gagal menghapus data!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                }).setMessage("Apakah yakin akan menghapus ? "+ data.getBuku());
                builder.show();
            }
        });

        holder.card_hasil.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                FragmentManager manager = ((AppCompatActivity)activity).getSupportFragmentManager();
                DialogForm dialog = new DialogForm(
                        data.getBuku(),
                        data.getKode(),
                        data.getKey(),
                        "Ubah"
                );
                dialog.show(manager, "form");
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        TextView tv_buku, tv_kode;
        ImageView btn_hapus;
        CardView card_hasil;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            tv_buku = itemView.findViewById(R.id.tv_buku);
            tv_kode = itemView.findViewById(R.id.tv_kode);
            card_hasil = itemView.findViewById(R.id.card_hasil);
            btn_hapus = itemView.findViewById(R.id.hapus);
        }
    }
}

