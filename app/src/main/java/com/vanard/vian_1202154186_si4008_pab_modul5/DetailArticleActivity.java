package com.vanard.vian_1202154186_si4008_pab_modul5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailArticleActivity extends AppCompatActivity {

    TextView tvAuthor, tvTitle, tvArticle, tvNotif;
    final String PREF_FONT_SIZE = "BigSize";
    SharedPreferences spFont;

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
        setContentView(R.layout.activity_detail_article);

        tvAuthor = findViewById(R.id.tv_author_detail);
        tvArticle = findViewById(R.id.tv_desc_detail);
        tvTitle = findViewById(R.id.tv_title_detail);
        tvNotif = findViewById(R.id.tv_notif_size_detail);

        if (getIntent() != null){
            tvAuthor.setText(getIntent().getStringExtra("penulis"));
            tvArticle.setText(getIntent().getStringExtra("deskripsi"));
            tvTitle.setText(getIntent().getStringExtra("judul"));
        }

        spFont = getSharedPreferences(PREF_FONT_SIZE, Context.MODE_PRIVATE);
        if (spFont.getBoolean(PREF_FONT_SIZE, false)){
            tvArticle.setTextSize(22);
            tvNotif.setVisibility(View.VISIBLE);
        }else{
            tvNotif.setVisibility(View.INVISIBLE);
            tvArticle.setTextSize(16);
        }
    }
}
