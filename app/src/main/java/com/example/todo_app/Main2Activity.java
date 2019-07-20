package com.example.todo_app;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    List<NotesPojo> notesLists = new ArrayList<>();;
    RecyclerView.Adapter adapters;
    TextView textView,textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        adapters = new todoadpter(notesLists,this);
        recyclerView = findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);
        textView=findViewById(R.id.Title);
        textView1=findViewById(R.id.Description);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        database = FirebaseDatabase.getInstance();
        databaseReference =database.getReference("Notes");




        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              notesLists.removeAll(notesLists);
                //All data change in database notify here
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    //Add data to pojo class
                    NotesPojo noteDatas = dataSnapshot1.getValue(NotesPojo.class);
                    notesLists.add(noteDatas);

                }
                //Saying to adapter on chnage on data
                adapters.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Main2Activity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent i = new Intent(Main2Activity.this,CreateActivity.class);
                startActivity(i);
            }
        });
        adapters = new todoadpter(notesLists, getApplicationContext());
        recyclerView.setAdapter(adapters);

    }

}
