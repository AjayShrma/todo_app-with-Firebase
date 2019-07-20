package com.example.todo_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.List;
import java.util.zip.Inflater;

import static android.media.CamcorderProfile.get;

public class todoadpter extends RecyclerView.Adapter<todoadpter.programViewholder> {

    List<NotesPojo> notesList;
    Context context;

    public  todoadpter(List<NotesPojo> notesList ,Context context){

        this.context = context;
        this.notesList = notesList;

    }
    @NonNull
    @Override
    public programViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.demo,parent,false);
        return new programViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull programViewholder holder, int position) {
        NotesPojo title = notesList.get(position);
        holder.textView.setText(title.getmTitle());
        holder.textView1.setText(title.getmTitle());
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class programViewholder extends RecyclerView.ViewHolder{
        TextView textView,textView1;
        public programViewholder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.Title);
            textView1=itemView.findViewById(R.id.Description);
            Button closing = itemView.findViewById(R.id.bClose);

            closing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NotesPojo pojoData = notesList.get(getAdapterPosition());
                    String id = pojoData.getmId();
                    DatabaseReference mDatabase;
                    mDatabase = FirebaseDatabase.getInstance().getReference();

                    mDatabase.child("Notes").child(id).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {


                        }
                    });




                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NotesPojo pojoData = notesList.get(getAdapterPosition());
                    Intent i = new Intent(context,Note_Detail_Activity.class);
                    i.putExtra("id",pojoData.getmId());
                    i.putExtra("title",pojoData.getmTitle());
                    i.putExtra("discrip",pojoData.getmDescription());

                    context.startActivity(i);

                }
            });
        }
    }
}
