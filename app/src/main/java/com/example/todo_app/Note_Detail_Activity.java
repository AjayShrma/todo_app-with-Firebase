package com.example.todo_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Note_Detail_Activity extends AppCompatActivity {
    EditText editTitle;
    EditText editDesc;
    Button Cancel;
    Button Done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note__detail_);
        editTitle=findViewById(R.id.Title);
        editDesc=findViewById(R.id.Desc);
        Cancel=findViewById(R.id.btnCancel);
        Done=findViewById(R.id.btndone);

        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("discrip");

        editTitle.setText(title);
        editDesc.setText(description);

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = getIntent().getStringExtra("id");
                DatabaseReference mDatabase;
                mDatabase = FirebaseDatabase.getInstance().getReference();

                mDatabase.child("Notes").child(id).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Note_Detail_Activity.this, "Deleted!!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Note_Detail_Activity.this,Main2Activity.class));
                        //   progressBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });


Done.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String    titlesend = editTitle.getText().toString();
        String    disSend = editDesc.getText().toString();
        String id = getIntent().getStringExtra("id");
        NotesPojo noteData = new NotesPojo(id,titlesend,disSend);
        mDatabase.child("Notes").child(id).setValue(noteData)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Note_Detail_Activity.this, "Updated", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Note_Detail_Activity.this,Main2Activity.class));
                        //    progressBar.setVisibility(View.INVISIBLE);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Note_Detail_Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
});

    }
}
