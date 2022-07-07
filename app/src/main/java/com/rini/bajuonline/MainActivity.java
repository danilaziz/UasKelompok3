package com.rini.bajuonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListBajuAdapter adapter;
    private RecyclerView rvBaju;
    private DatabaseReference reference;
    private ArrayList<Baju> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvBaju = findViewById(R.id.recyclerView);
        rvBaju.setHasFixedSize(true);

        reference = FirebaseDatabase.getInstance().getReference().child("baju");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Baju club = dataSnapshot.getValue(Baju.class);
                    list.add(club);
                }
                showRecyclerList();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void showRecyclerList(){
        rvBaju.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListBajuAdapter(getApplicationContext(), list);
        rvBaju.setAdapter(adapter);
        adapter.setOnItemClickCallback(this::showSelectedBaju);
    }

    private void showSelectedBaju(Baju baju) {
        Intent intent = new Intent(MainActivity.this,DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_NAME, baju.getNama());
        intent.putExtra(DetailActivity.EXTRA_GAMBAR, baju.getGambar());
        intent.putExtra(DetailActivity.EXTRA_HARGA, baju.getHarga());
        startActivity(intent);
    }
}