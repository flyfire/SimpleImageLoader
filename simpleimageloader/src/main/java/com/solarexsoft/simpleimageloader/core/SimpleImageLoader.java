package com.solarexsoft.simpleimageloader.core;

import android.widget.ImageView;

import com.solarexsoft.simpleimageloader.config.DisplayConfig;
import com.solarexsoft.simpleimageloader.config.ImageLoaderConfig;

/**
 * <pre>
 *    Author: houruhou
 *    Project: https://solarex.github.io/projects
 *    CreatAt: 12/06/2017
 *    Desc:
 * </pre>
 */

public class SimpleImageLoader {
    private ImageLoaderConfig mConfig;
    private RequestQueue mRequestQueue;
    private static volatile SimpleImageLoader sLoader;

    private SimpleImageLoader(ImageLoaderConfig config) {
        this.mConfig = config;
        mRequestQueue = new RequestQueue(config.getThreadCount());
        mRequestQueue.start();
    }

    public static SimpleImageLoader getInstance(ImageLoaderConfig config) {
        if (sLoader == null) {
            synchronized (SimpleImageLoader.class) {
                if (sLoader == null) {
                    sLoader = new SimpleImageLoader(config);
                }
            }
        }
        return sLoader;
    }

    public static SimpleImageLoader getInstance() {
        if (sLoader == null) {
            throw new IllegalStateException("Call getInstance(ImageLoaderConfig) to initialize " +
                    "first!");
        }
        return sLoader;
    }

    public void displayImage(ImageView imageView, String url) {
        displayImage(imageView, url, null, null);
    }

    public void displayImage(ImageView imageView, String url, DisplayConfig displayConfig,
                             ImageListener imageListener) {
        BitmapRequest request = new BitmapRequest(imageView, url, displayConfig, imageListener);
        mRequestQueue.addRequest(request);
    }

    public ImageLoaderConfig getConfig() {
        return mConfig;
    }
}
