package com.vanard.vian_1202154186_si4008_pab_modul5;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleHolder> {

    private Context context;
    private List<Article> mList;

    public ArticleAdapter(Context context, List<Article> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ArticleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArticleHolder(LayoutInflater.from(context).inflate(R.layout.item_article, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdapter.ArticleHolder holder, int position) {
        final Article data = mList.get(position);

        holder.tvPublisher.setText(data.penulis);
        holder.tvTitle.setText(data.judul);
        holder.tvDesc.setText(data.deskripsi);

        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Share Intent: " + data.judul, Toast.LENGTH_SHORT).show();
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, DetailArticleActivity.class);
                i.putExtra("penulis", data.penulis + ", " + data.created_at);
                i.putExtra("judul", data.judul);
                i.putExtra("deskripsi", data.deskripsi);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public Article getDataArticle(int position){
        return mList.get(position);
    }

    class ArticleHolder extends RecyclerView.ViewHolder {

        private TextView tvPublisher, tvTitle, tvDesc;
        private ImageView btnShare;
        private CardView cardView;

        ArticleHolder(@NonNull View itemView) {
            super(itemView);

            tvPublisher = itemView.findViewById(R.id.tv_publisher);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            btnShare = itemView.findViewById(R.id.img_share_item);
            cardView = itemView.findViewById(R.id.card_item_article);
        }
    }
}
