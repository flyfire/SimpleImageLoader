package com.solarexsoft.simpleimageloader.loader;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.solarexsoft.simpleimageloader.cache.BitmapCache;
import com.solarexsoft.simpleimageloader.config.DisplayConfig;
import com.solarexsoft.simpleimageloader.core.BitmapRequest;
import com.solarexsoft.simpleimageloader.core.SimpleImageLoader;

/**
 * <pre>
 *    Author: houruhou
 *    Project: https://solarex.github.io/projects
 *    CreatAt: 12/06/2017
 *    Desc:
 * </pre>
 */

public abstract class AbstractLoader implements Loader {
    private BitmapCache mCache = SimpleImageLoader.getInstance().getConfig().getCache();
    private DisplayConfig mConfig = SimpleImageLoader.getInstance().getConfig().getConfig();

    @Override
    public void loadImage(BitmapRequest request) {
        Bitmap bitmap = mCache.get(request);
        if (bitmap == null) {
            showLoadingImage(request);
            bitmap = onLoad(request);
            cacheBitmap(request, bitmap);
        }
        deliveryToUIThread(request, bitmap);
    }

    protected void showLoadingImage(BitmapRequest request) {
        if (mConfig != null && mConfig.loadingImageResource > 0) {
            final ImageView imageView = request.getImageView();
            if (imageView != null) {
                imageView.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageResource(mConfig.loadingImageResource);
                    }
                });
            }
        }
    }

    protected abstract Bitmap onLoad(BitmapRequest request);

    private void cacheBitmap(BitmapRequest request, Bitmap bitmap) {
        if (request != null && bitmap != null) {
            synchronized (AbstractLoader.class) {
                mCache.put(request, bitmap);
            }
        }
    }

    private void deliveryToUIThread(final BitmapRequest request, final Bitmap bitmap) {
        final ImageView imageView = request.getImageView();
        if (imageView != null) {
            imageView.post(new Runnable() {
                @Override
                public void run() {
                    //ImageListener如果不为空，可以加入后续ImageListener对bitmap的处理
                    if (bitmap != null) {
                        imageView.setImageBitmap(bitmap);
                    }
                    if (bitmap == null && mConfig != null && mConfig
                            .failedImageResource != -1) {
                        imageView.setImageResource(request.getConfig().failedImageResource);
                    }
                    if (request.getListener() != null) {
                        request.getListener().onComplete(imageView, bitmap, request.getImageUrl());
                    }
                }
            });
        }
    }
}
