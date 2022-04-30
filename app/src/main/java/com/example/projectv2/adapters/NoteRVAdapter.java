package com.example.projectv2.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectv2.R;
import com.example.projectv2.activities.MainActivity;
import com.example.projectv2.activities.VieworUpdateNote;
import com.example.projectv2.entities.Note;

import java.util.ArrayList;


public class NoteRVAdapter extends RecyclerView.Adapter<NoteRVAdapter.ViewHolder> {

    private ArrayList<Note> noteArrayList;
    private Context context;

    public NoteRVAdapter(ArrayList<Note> noteArrayList, Context context){
        this.noteArrayList = noteArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_containter_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteRVAdapter.ViewHolder holder, int position) {
        Note note = noteArrayList.get(position);
        holder.tv_title.setText(note.getTitle());
        holder.tv_subtitle.setText(note.getSubtitle());
        holder.tv_dateTime.setText(note.getDateTime());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, VieworUpdateNote.class);

                intent.putExtra("id", String.valueOf(note.getId()));
                intent.putExtra("title", note.getTitle());
                intent.putExtra("subtitle", note.getSubtitle());
                intent.putExtra("datatime" ,note.getDateTime());
                intent.putExtra("notetext", note.getNoteText());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return noteArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView tv_title, tv_subtitle, tv_dateTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            tv_title = itemView.findViewById(R.id.tv_Title);
            tv_subtitle = itemView.findViewById(R.id.tv_subtitle);
            tv_dateTime = itemView.findViewById(R.id.tv_dateTime);
        }
    }
}
