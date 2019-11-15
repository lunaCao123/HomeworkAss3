package com.example.catcrazeapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SearchresultAdapter extends RecyclerView.Adapter<SearchresultAdapter
        .SearchViewHolder> {

    private List<Cat>catsToAdapt;

    public void setData(List<Cat>catsToAdapt){
        this.catsToAdapt=catsToAdapt;
    }

    @NonNull
    @Override
    public SearchresultAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                   int viewType) {

        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cat_layout,parent,false);

        SearchViewHolder searchViewHolder = new SearchViewHolder(view);

        return searchViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchresultAdapter
            .SearchViewHolder holder, int position) {
        final Cat catAtPosition = catsToAdapt.get(position);

        holder.catBreedTextView.setText(catAtPosition.getName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, CatDetailActivity.class);
                //Pass 'id' to the next page, so user can find the matching cat details
                intent.putExtra("id", catAtPosition.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return catsToAdapt.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView catBreedTextView;

        public SearchViewHolder(View v) {
            super(v);
            view = v;
            catBreedTextView = v.findViewById(R.id.cat_catnameText);
        }
    }
}
