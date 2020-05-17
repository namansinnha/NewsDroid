package com.developer.coreandroidx.newsdroid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.developer.coreandroidx.newsdroid.Model.NewsJsonResponse;
import com.developer.coreandroidx.newsdroid.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    Context mContext;
    ArrayList<NewsJsonResponse> mNewsJsonResponse;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public NewsAdapter(Context mContext, ArrayList<NewsJsonResponse> mNewsJsonResponse) {
        this.mContext = mContext;
        this.mNewsJsonResponse = mNewsJsonResponse;
    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.news_content_layout, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder holder, int position) {

        final NewsJsonResponse newsJsonResponse = mNewsJsonResponse.get(position);

        String news_image = newsJsonResponse.getNews_image_url();
        String news_name = newsJsonResponse.getNews_name();
        String news_title = newsJsonResponse.getNews_title();
        String news_date_published = newsJsonResponse.getNews_published_date();

        Glide.with(mContext).load(news_image).centerCrop().fitCenter().placeholder(R.drawable.ic_google_news).into(holder.newsImage);
        holder.newsName.setText(news_name);
        holder.newsTitle.setText(news_title);
        holder.newsPublishedDate.setText(news_date_published);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String urlToWeb = newsJsonResponse.getNews_url();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(urlToWeb));
                mContext.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return mNewsJsonResponse.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout mainLayout;
        CircleImageView newsImage;
        TextView newsName, newsTitle, newsPublishedDate;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            mainLayout = itemView.findViewById(R.id.mainLayout);
            newsImage = itemView.findViewById(R.id.news_image);
            newsName = itemView.findViewById(R.id.news_name);
            newsTitle = itemView.findViewById(R.id.news_title);
            newsPublishedDate = itemView.findViewById(R.id.news_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mListener != null) {

                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION) {

                            mListener.OnItemClick(position);

                        }
                    }

                }
            });
        }
    }
}
