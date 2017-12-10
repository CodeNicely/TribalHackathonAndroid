package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.image_loader;

import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * Created by meghal on 13/10/16.
 */

public interface ImageLoader {

    void loadImage(String url, ImageView imageView, ProgressBar progressBar);

    void loadImage1(String image_url, ImageView aah_imageView);
//    void load_circular_image(String url, ImageView imageView);
}
