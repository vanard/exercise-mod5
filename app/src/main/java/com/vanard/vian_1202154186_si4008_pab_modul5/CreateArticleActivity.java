package com.vanard.vian_1202154186_si4008_pab_modul5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateArticleActivity extends AppCompatActivity {

    EditText etTitle, etArticle, etAuthor;
    Button btnPost;
    String mTitle, mArticle, mAuthor;
    private ArticleDbHelper db;

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
        setContentView(R.layout.activity_create_article);

        db = new ArticleDbHelper(this);
        etTitle = findViewById(R.id.et_title_create);
        etArticle = findViewById(R.id.et_desc_create);
        etAuthor = findViewById(R.id.et_author_create);
        btnPost = findViewById(R.id.btn_post_create);

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postArticle();
            }
        });

    }

    private void postArticle() {
        mTitle = etTitle.getText().toString().trim();
        mArticle = etArticle.getText().toString().trim();
        mAuthor = etAuthor.getText().toString().trim();

        if (mTitle.isEmpty()){
            msg("Title kosong");
            return;
        }
        if (mArticle.isEmpty()){
            msg("Article kosong");
            return;
        }
        if (mAuthor.isEmpty()){
            msg("Author kosong");
            return;
        }

        saveToDb();

    }

    private void saveToDb() {
        if (db.insertData(new Article(mAuthor, mTitle, mArticle, getDateTime()))){
            msg("Data insyallah berhasil ditambahkan");
            startActivity(new Intent(CreateArticleActivity.this, ListArticleActivity.class));
        }else {
            msg("Something wrong");
        }
    }

    private void msg(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "EEE d MMM `yy", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
