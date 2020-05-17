package com.developer.coreandroidx.newsdroid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.developer.coreandroidx.newsdroid.Model.NewsJsonResponse;
import com.developer.coreandroidx.newsdroid.Model.SourceJsonResponse;
import com.developer.coreandroidx.newsdroid.R;

import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.Random;

public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.SourceViewHolder> {

    Context mContext;
    ArrayList<SourceJsonResponse> mSourceJsonResponses;

    public SourceAdapter(Context mContext, ArrayList<SourceJsonResponse> mSourceJsonResponses) {
        this.mContext = mContext;
        this.mSourceJsonResponses = mSourceJsonResponses;
    }

    @NonNull
    @Override
    public SourceAdapter.SourceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.source_content_layout, parent, false);
        return new SourceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SourceAdapter.SourceViewHolder holder, int position) {
        final SourceJsonResponse sourceJsonResponse = mSourceJsonResponses.get(position);

        String news_name = sourceJsonResponse.getNewsName();
        String news_desc = sourceJsonResponse.getNewsDesc();
        String news_category = sourceJsonResponse.getNewsCategory();
        String news_language = sourceJsonResponse.getNewsLanguage();
        String news_country = sourceJsonResponse.getNewsCountry();

        holder.source_news_name.setText(news_name);
        holder.source_desc.setText(news_desc);
        holder.source_category.setText(news_category);
        holder.source_language.setText(news_language);
        holder.source_country.setText(news_country);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String urlToWeb = sourceJsonResponse.getNewsUrl();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(urlToWeb));
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mSourceJsonResponses.size();
    }

    public class SourceViewHolder extends RecyclerView.ViewHolder {

        private TextView source_news_name, source_desc, source_category, source_language, source_country;
        private RelativeLayout mainLayout;

        public SourceViewHolder(@NonNull View itemView) {
            super(itemView);

            mainLayout = itemView.findViewById(R.id.mainLayout);
            source_news_name = itemView.findViewById(R.id.source_news_name);
            source_desc = itemView.findViewById(R.id.source_news_desc);
            source_category = itemView.findViewById(R.id.source_category);
            source_language = itemView.findViewById(R.id.source_language);
            source_country = itemView.findViewById(R.id.source_country);

        }
    }
}
