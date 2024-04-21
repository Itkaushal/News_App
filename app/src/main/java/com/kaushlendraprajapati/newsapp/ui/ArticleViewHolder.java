package com.kaushlendraprajapati.newsapp.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.kaushlendraprajapati.newsapp.R;
import com.kaushlendraprajapati.newsapp.modals.Article;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ArticleViewHolder extends RecyclerView.ViewHolder {
    private final TextView titleTextView;
    private final TextView descriptionTextView;

    private final TextView urlTextView;
    private final ImageView urlToImageView;
    private final TextView author;
    private final TextView publish;
    private final  ImageView save;
    private final ImageView share;
    private Article article;

    public ArticleViewHolder(@NonNull View itemView) {
        super(itemView);
        titleTextView = itemView.findViewById(R.id.titleTextView);
        descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        urlTextView= itemView.findViewById(R.id.urlTextView);
        urlToImageView= itemView.findViewById(R.id.imageView);
        author=itemView.findViewById(R.id.authorTextview);
        publish=itemView.findViewById(R.id.publishTextView);
        save=itemView.findViewById(R.id.image_download);
        share=itemView.findViewById(R.id.image_share);

        // save imgae
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (article != null) {
                    Context context = itemView.getContext();
                    String imageUrl = article.urlToImage; //
                    String fileName = "NewsImage" + getAdapterPosition();

                    ImageUtils.saveImage(context, imageUrl, fileName);
                } else {
                    Toast.makeText(itemView.getContext(), "No article to save", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // share news with content

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (article != null) {
                    Context context = itemView.getContext();
                    shareArticle(context, article);
                } else {
                    Toast.makeText(itemView.getContext(), "No article to share", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void shareArticle(Context context, Article article) {

        Picasso.get().load(article.urlToImage).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                try {
                    File cachePath = new File(context.getCacheDir(), "images");
                    cachePath.mkdirs();
                    FileOutputStream stream = new FileOutputStream(cachePath + "/image.png");
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    stream.close();

                    File newFile = new File(cachePath, "/image.png");
                    Uri contentUri = FileProvider.getUriForFile(
                            context,
                            "com.kaushlendraprajapati.newsapp.fileprovider",
                            newFile
                    );

                    if (contentUri != null) {
                        Intent shareIntent = new Intent();
                        shareIntent.setAction(Intent.ACTION_SEND);
                        shareIntent.putExtra(Intent.EXTRA_TEXT, article.title + "\n" + article.description + "\n" + article.url);
                        shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
                        shareIntent.setType("image/png");
                        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                        context.startActivity(Intent.createChooser(shareIntent, "Share with"));
                    }
                } catch (IOException e) {
                    Toast.makeText(context, "Failed to share image", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                Toast.makeText(context, "Error loading image", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                // No action required
            }
        });
    }


    public void bind(Article article) {
        this.article=article;
        titleTextView.setText(article.title);
        descriptionTextView.setText(article.description);
        urlTextView.setText(article.url);
        Picasso.get().load(article.urlToImage).into(urlToImageView);
        author.setText(article.author);
        publish.setText(String.valueOf(article.publishedAt));

    }
}

