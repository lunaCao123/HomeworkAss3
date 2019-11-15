package com.example.catcrazeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SearchRecyclerFragment extends Fragment{

    private RecyclerView recyclerView;
    private EditText searchEditText;
    private String searchInput;
    public ImageButton search_button;
    private List<Cat>catList;
    private Cat[]catsArray;
    private TextView setSearchText;
    private CatDatabase catDb;


    public SearchRecyclerFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_searchresult_recycler,
                container, false);
        recyclerView = view.findViewById(R.id.searchresult_rv);
        recyclerView.setVisibility(View.INVISIBLE);
        search_button = view.findViewById(R.id.searchButton);
        searchEditText = view.findViewById(R.id.searchText);
        setSearchText = view.findViewById(R.id.textSearch);
        searchEditText.setVisibility(View.VISIBLE);
        setSearchText.setVisibility(View.INVISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        final SearchresultAdapter searchresultAdapter = new SearchresultAdapter();

        //Connect to url
        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
        String url =
                "https://api.thecatapi.com/v1/breeds?api_key=8f425810-b96e-4594-b05e-0b90df4a2051";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("Request Responded");
                Gson gson = new Gson();
                catsArray = gson.fromJson(response, Cat[].class);//read the output of the API
                catList = Arrays.asList(catsArray);
                catDb = CatDatabase.getInstance(getContext());
                ArrayList<Cat>newList = new ArrayList<Cat>(catList);
                catDb.catDAO().insertAll(newList); //Save to database
                searchresultAdapter.setData(catList);
                recyclerView.setAdapter(searchresultAdapter);
                //Search button to search for user input, then creat new list to populate
                // in recyclerview
                search_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        searchInput = searchEditText.getText().toString();
                        setSearchText.setText(searchInput);
                        String input = setSearchText.getText().toString();
                        String userInput = input.toLowerCase();
                        List<Cat>newCatList = new ArrayList<>();
                        for(Cat name:catList){
                            if(name.getName().toLowerCase().contains(userInput)){
                                newCatList.add(name);
                            }
                        }
                        searchresultAdapter.setData(newCatList);
                        recyclerView.setAdapter(searchresultAdapter);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("The Request Failed");
            }

        });
        requestQueue.add(stringRequest);
        return view;

    }
    @Override
    public void onResume(){
        super.onResume();
        MainActivity parent = (MainActivity)getActivity();
    }

}
