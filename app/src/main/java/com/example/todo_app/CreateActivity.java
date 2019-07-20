package com.example.todo_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static androidx.core.os.LocaleListCompat.create;

public class CreateActivity extends AppCompatActivity {
    Button create;
    EditText title,disc;
    String id;
    FirebaseDatabase database ;
    DatabaseReference mDataBase;
    String titlesend,disSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        title = findViewById(R.id.setTitle);
        disc = findViewById(R.id.setDesc);
        create=findViewById(R.id.create);
        database = FirebaseDatabase.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference();

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Addnotes();
            }
        }); }

    private  void  Addnotes(){
        titlesend = title.getText().toString();
        disSend = disc.getText().toString();
        try {
            id = mDataBase.push().getKey();
        }catch (Exception e){}
        NotesPojo noteData = new NotesPojo(id,disSend,titlesend);
        mDataBase.child("Notes").child(id).setValue(noteData)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(CreateActivity.this, "Done", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(CreateActivity.this,Main2Activity.class));
                        //    progressBar.setVisibility(View.INVISIBLE);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CreateActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
