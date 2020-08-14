package com.example.fisher_hanbook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Text_Content_Activity extends AppCompatActivity {
    private ActionBar actionBar;
    private TextView text_content;
    private ImageView iContent;
    private SharedPreferences def_pref;
    private Typeface face1;
    private int category = 0;
    private int position = 0;
    private int[] array_fish = {R.string.fish_1,R.string.fish_2,R.string.fish_3,R.string.fish_4,R.string.fish_5};
    private int[] array_na = {R.string.na_1,R.string.na_2,R.string.na_3,R.string.na_4};
    private int[] array_img_fish = {R.drawable.carp,R.drawable.wyka,R.drawable.som,R.drawable.osetr, R.drawable.nalim};
    private String[] array_tittle_fish = {"Karp","Wuka","Som","Osetr", "Nalim"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Mysal");
        setContentView(R.layout.text_content);
        def_pref = PreferenceManager.getDefaultSharedPreferences(this);
        text_content = findViewById(R.id.textView);
        iContent = findViewById(R.id.image_content);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBar = getSupportActionBar();
        reciveIntent();
        String text = def_pref.getString("main_text_size", "Средний");
        String font = def_pref.getString("main_text_font", "Anton-Regular");
        if(text !=null) {
            switch (text) {
                case "Большой":
                    text_content.setTextSize(24);
                    break;
                case "Средний":
                    text_content.setTextSize(18);
                    break;
                case "Маленький":
                    text_content.setTextSize(14);

                    break;
            }
        }
        if(font != null){
            switch (font){
                case "Anton-Regular":
                    face1 = Typeface.createFromAsset(this.getAssets(),"fonts/Anton-Regular.ttf");
                    break;
                case "Lobster-Regular":
                    face1 = Typeface.createFromAsset(this.getAssets(),"fonts/Lobster-Regular.ttf");
                    break;
                case "JosefinSans-VariableFont_wght":
                    face1 = Typeface.createFromAsset(this.getAssets(),"fonts/JosefinSans-VariableFont_wght.ttf");
                    break;
                case "JosefinSans-Italic-VariableFont_wght":
                    face1 = Typeface.createFromAsset(this.getAssets(),"fonts/JosefinSans-Italic-VariableFont_wght.ttf");
                    break;
                case "Inconsolata-VariableFont_wdth,wght":
                    face1 = Typeface.createFromAsset(this.getAssets(),"fonts/Inconsolata-VariableFont_wdth,wght.ttf");
                    break;
            }
            text_content.setTypeface(face1);
        }
    }

    private void reciveIntent(){
        Intent i = getIntent();
        if(i != null){
            category = i.getIntExtra("category",0);
            position = i.getIntExtra("position",0);
        }

        switch (category){
            case 0:
                iContent.setImageResource(array_img_fish[position]);
                text_content.setText(array_fish[position]);
                actionBar.setTitle(array_tittle_fish[position]);
                break;
            case 1:
                text_content.setText(array_na[position]);
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;

        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    private void init(){

    }
}
