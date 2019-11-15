package com.example.catcrazeapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

public class CatDetailActivity extends AppCompatActivity {
    private TextView catBreedTextView;
    private TextView catOriginTextView;
    private TextView catLifeTextView;
    private TextView catTemeramentTextView;
    private TextView catDogFriendTextView;
    private TextView catDescriptionTextView;
    private TextView catWikiTextView;
    private ImageView catImage;
    private ConstraintLayout catConLayout;
    private ImageButton fav_button;
    public boolean isFavourite = false;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_detail);

        catConLayout = findViewById(R.id.cat_cl);
        catBreedTextView = catConLayout.findViewById(R.id.catDetail_breedName);
        catOriginTextView = catConLayout.findViewById(R.id.catDetail_originText);
        catLifeTextView = catConLayout.findViewById(R.id.catDetail_lifeSpanText);
        catTemeramentTextView = catConLayout.findViewById(R.id.catDetail_temperamentText);
        catDogFriendTextView = catConLayout.findViewById(R.id.catDetail_dogLevelText);
        catDescriptionTextView = catConLayout.findViewById(R.id.catDetail_description);
        catWikiTextView = catConLayout.findViewById(R.id.catDetail_wikiText);
        catImage = catConLayout.findViewById(R.id.catDetail_image);
        fav_button = catConLayout.findViewById(R.id.catDetail_FavouriteBtn);
        backBtn = catConLayout.findViewById(R.id.catDetail_backBtn);
        //find the selected cat detail by match the id, since 'id' is not an integer, therefore
        //I used getStringExtra.
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        CatDatabase catDb = CatDatabase.getInstance(this);
        final Cat cat = catDb.catDAO().findCatById(id);


        catBreedTextView.setText(cat.getName());
        catOriginTextView.setText(cat.getOrigin());
        catLifeTextView.setText(cat.getLife_span());
        catTemeramentTextView.setText(cat.getTemperament());
        int doglvl = cat.getDog_friendly();
        String dogFriend = Integer.toString(doglvl);
        catDogFriendTextView.setText(dogFriend);
        catDescriptionTextView.setText(cat.getDescription());
        catWikiTextView.setText(cat.getWikipedia_url());
        fav_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFavourite){
                    fav_button.setImageResource(R.drawable.ic_favorite_border_red);
                }else{
                    fav_button.setImageResource(R.drawable.ic_favorite_red);
                    //Save Favourite Cat to the Faourite Catlist
                    FavouriteRecyclerFragment.favCatList.add(cat);
                }
                isFavourite = !isFavourite;
            }
        });

        //imageUrl didn't work because imageUrl does not exsist in /breeds url address. It only
        //exsist in https://api.thecatapi.com/images/search?breed_id={{selected_breed.id}},
        //but i'm not sure how to implement it. So I've set all image to the same picture
        String imageUrl = cat.getUrl();
        if(imageUrl != null){
            Glide.with(this).load(imageUrl).into(catImage);
        }else{
            catImage.setImageResource(R.drawable.cat);
        }

    }
    //Back to Main Activity
    public void setBackBtn (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
