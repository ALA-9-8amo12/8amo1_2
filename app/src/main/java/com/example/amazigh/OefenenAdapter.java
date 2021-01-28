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

public class OefenenAdapter extends RecyclerView.Adapter<OefenenAdapter.OefenenViewholder> {
    Context context;
    List<OefenenModel> oefenenModel;

    public OefenenAdapter(Context context, List<OefenenModel> oefenenmodel) {
        this.context = context;
        this.oefenenModel = oefenenmodel;
    }

    @NonNull
    @Override
    public OefenenViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.oefenen_cardview, parent, false);
        return new OefenenViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OefenenAdapter.OefenenViewholder holder, final int position) {

        Glide.with(context).load(oefenenModel.get(position).getFoto_bestandsnaam()).into(holder.Foto_bestandsnaam);
        holder.Geluids_bestandsnaam.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                try {
                    geluid(oefenenModel.get(position).getGeluids_bestandsnaam());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        holder.AMAZIGH.setText(oefenenModel.get(position).getAMAZIGH());
        holder.NL.setText(oefenenModel.get(position).getNL());
    }

    @Override
    public int getItemCount() {
        return oefenenModel.size();
    }

    class OefenenViewholder extends RecyclerView.ViewHolder {

        Button Geluids_bestandsnaam;
        ImageView Foto_bestandsnaam;
        TextView AMAZIGH;
        TextView NL;

        public OefenenViewholder(@NonNull View itemView) {

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
