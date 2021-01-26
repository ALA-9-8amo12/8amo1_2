package com.example.amazigh;


import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.io.IOException;
import java.util.List;

public class SpelenAdapter extends RecyclerView.Adapter<SpelenAdapter.SpelenViewholder> {
    Context context;
    List<SpelenModel> spelenModel;

    public SpelenAdapter(Context context, List<SpelenModel> spelenmodel) {
        this.context = context;
        this.spelenModel = spelenmodel;
    }

    @NonNull
    @Override
    public SpelenViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spelen_cardview, parent, false);
        return new SpelenViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpelenAdapter.SpelenViewholder holder, final int position) {

        Glide.with(context).load(spelenModel.get(position).getFoto_bestandsnaam()).into(holder.Foto_bestandsnaam);
        holder.Geluids_bestandsnaam.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                try {
                    geluid(spelenModel.get(position).getGeluids_bestandsnaam());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        holder.AMAZIGH.setText(spelenModel.get(position).getAMAZIGH());
        holder.NL.setText(spelenModel.get(position).getNL());
    }

    @Override
    public int getItemCount() {
        return spelenModel.size();
    }

    class SpelenViewholder extends RecyclerView.ViewHolder {

        Button Geluids_bestandsnaam;
        ImageView Foto_bestandsnaam;
        TextView AMAZIGH;
        TextView NL;

        public SpelenViewholder(@NonNull View itemView) {

            super(itemView);

            NL = itemView.findViewById(R.id.NL);
            Foto_bestandsnaam = itemView.findViewById(R.id.Foto_bestandsnaam);
            Geluids_bestandsnaam = itemView.findViewById(R.id.Geluids_bestandsnaam);
            AMAZIGH = itemView.findViewById(R.id.AMAZIGH);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void geluid (String path) throws IOException {
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        mediaPlayer.setDataSource(path);
        mediaPlayer.prepare();
        mediaPlayer.start();
    }
}
