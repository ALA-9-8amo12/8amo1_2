package com.example.amazigh;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategorieAdapter extends RecyclerView.Adapter<CategorieAdapter.CategoryViewholder> {

    Context context;
    ArrayList<CategorieModel> categoryModel;

    public CategorieAdapter(Context context, ArrayList<CategorieModel> categorymodel) {
        this.context = context;
        this.categoryModel = categorymodel;
    }


    @NonNull
    @Override
    public CategoryViewholder

    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categorie_cardview, parent, false);
        return new CategoryViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewholder holder, int position) {

        holder.categories.setText(categoryModel.get(position).getCategorieën());
        holder.categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,OefenenActivity.class);
                intent.putExtra("category", categoryModel.get(position).getCategorieën());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryModel.size();
    }

    class CategoryViewholder extends RecyclerView.ViewHolder {

        Button categories;

        public CategoryViewholder(@NonNull View itemView) {
            super(itemView);
            categories = itemView.findViewById(R.id.categorie);
        }
    }

}
