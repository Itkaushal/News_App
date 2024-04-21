package com.kaushlendraprajapati.newsapp.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class ImageUtils {
    public static void saveImage(Context context, String imageUrl, String fileName) {
        Picasso.get().load(imageUrl).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                try {
                    File storageDir = new File(context.getExternalFilesDir(null), "NewsImage");
                    if (!storageDir.exists()) {
                        storageDir.mkdirs();
                    }

                    File imageFile = new File(storageDir, fileName + "_" + UUID.randomUUID().toString() + ".jpg");
                    FileOutputStream out = new FileOutputStream(imageFile);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                    out.close();

                    Toast.makeText(context, "Image saved to " + imageFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    Toast.makeText(context, "Failed to save image!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                Toast.makeText(context, "Failed to load image!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }

        });
    }
}
