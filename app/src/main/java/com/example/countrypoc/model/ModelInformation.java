package com.example.countrypoc.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.countrypoc.R;


public class ModelInformation {
    String title;
    String description;
    String imageHref;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }

    // important code for loading image here
    @BindingAdapter({ "avatar" })
    public static void loadImage(final ImageView imageView, String imageURL) {

        Glide.with(imageView.getContext())

                .setDefaultRequestOptions(new RequestOptions()
                        )
                .load(imageURL)
                .error(R.drawable.thump)
                .placeholder(R.drawable.thump)
                .into(imageView);


    }
}
