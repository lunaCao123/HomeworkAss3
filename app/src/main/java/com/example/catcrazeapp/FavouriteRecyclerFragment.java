package com.example.catcrazeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class FavouriteRecyclerFragment extends Fragment {

    private RecyclerView recyclerView;
    public static List<Cat> favCatList = new ArrayList<>();//Favourite Cat List

    public FavouriteRecyclerFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite_recycler,
                container, false);
        recyclerView = view.findViewById(R.id.favourite_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        final SearchresultAdapter searchresultAdapter = new SearchresultAdapter();
        searchresultAdapter.setData(favCatList);
        recyclerView.setAdapter(searchresultAdapter);

        return view;

    }
}
