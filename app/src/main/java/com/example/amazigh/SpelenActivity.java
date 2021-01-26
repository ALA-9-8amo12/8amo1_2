package com.example.amazigh;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class SpelenActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    ViewPager2 viewPager2;
    List<SpelenModel> list;
    String category;
    SpelenAdapter adapter;
    TextView titlecategory;


    private static final String TAG = "SpelenActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spelen);


        titlecategory = findViewById(R.id.category_title);

        viewPager2 = findViewById(R.id.viewpager);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        list = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference();


        getCategory();
        getData();
    }


    public void getCategory(){
        if(getIntent().hasExtra("category")){
            category = getIntent().getStringExtra("category");
            titlecategory.setText(category);
        }
    }


    public void getData(){
        Query query = databaseReference.child("Catogorien").child(category);

        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChildren()){
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        SpelenModel spelenModel = new SpelenModel();

                        spelenModel.setFoto_bestandsnaam(snapshot.child("Foto_bestandsnaam").getValue(String.class));
                        spelenModel.setGeluids_bestandsnaam(snapshot.child("Geluids_bestandsnaam").getValue(String.class));
                        spelenModel.setAMAZIGH(snapshot.child("AMAZIGH").getValue(String.class));
                        spelenModel.setNL(snapshot.child("NL").getValue(String.class));

                        list.add(spelenModel);
                    }

                    adapter = new SpelenAdapter(getApplicationContext(), list);
                    viewPager2.setAdapter(adapter);
                }

                else {
                    Log.d(TAG, "elseDataChange: no data");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });
    }
}
