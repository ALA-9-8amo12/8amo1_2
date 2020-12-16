package com.example.amazigh;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CategorieActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CategorieAdapter adapter;
    DatabaseReference mbase;
    ArrayList<CategorieModel> listmodel;
    LinearLayoutManager linearLayoutManager;
    private static final String TAG = "CategoryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorie);

        mbase = FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById(R.id.recycler1);
        listmodel = new ArrayList<>();

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        getData();
    }

    public void getData() {

        Query query = mbase.child("Catogorien");

        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        CategorieModel categoryModel = new CategorieModel();

                        categoryModel.setCategorieën(snapshot.getKey());

                        listmodel.add(categoryModel);
                    }

                    adapter = new CategorieAdapter(getApplicationContext(), listmodel);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

                    else{
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
