package com.vanard.vian_1202154186_si4008_pab_modul5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ListArticleActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArticleAdapter articleAdapter;
    List<Article> articleList;
    ArticleDbHelper dbHelper;

    final String PREF_NIGHT_MODE = "NightMode";
    SharedPreferences spNight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.DarkTheme);
        }else{
            setTheme(R.style.AppTheme);

            spNight = getSharedPreferences(PREF_NIGHT_MODE , Context.MODE_PRIVATE);
            if(spNight.getBoolean(PREF_NIGHT_MODE,false)){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_article);

        dbHelper = new ArticleDbHelper(this);
        recyclerView = findViewById(R.id.rv_article);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        articleList = new ArrayList<>();

        dbHelper.readData(articleList);

        articleAdapter = new ArticleAdapter(this, articleList);
        recyclerView.setAdapter(articleAdapter);

        articleAdapter.notifyDataSetChanged();
    }
}
