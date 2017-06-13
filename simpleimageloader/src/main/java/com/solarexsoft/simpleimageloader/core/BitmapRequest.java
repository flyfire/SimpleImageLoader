package com.solarexsoft.simpleimageloader.core;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.solarexsoft.simpleimageloader.config.DisplayConfig;
import com.solarexsoft.simpleimageloader.loadpolicy.LoadPolicy;
import com.solarexsoft.simpleimageloader.utils.MD5Utils;

import java.lang.ref.SoftReference;

/**
 * <pre>
 *    Author: houruhou
 *    Project: https://solarex.github.io/projects
 *    CreatAt: 12/06/2017
 *    Desc:
 * </pre>
 */

public class BitmapRequest implements Comparable<BitmapRequest> {
    private SoftReference<ImageView> mImageViewSoft;
    private String mImageUrl;
    private String mImageUrlMD5;
    public ImageListener mListener;
    private DisplayConfig mConfig;

    private LoadPolicy mLoadPolicy = SimpleImageLoader.getInstance().getConfig().getLoadPolicy();
    private int mSerialNum;

    public BitmapRequest(ImageView imageView, String imageUrl, DisplayConfig displayConfig,
                         ImageListener imageListener) {
        this.mImageViewSoft = new SoftReference<ImageView>(imageView);
        imageView.setTag(imageUrl);
        this.mImageUrl = imageUrl;
        this.mImageUrlMD5 = MD5Utils.toMD5(imageUrl);
        if (displayConfig != null) {
            this.mConfig = displayConfig;
        }
        if (imageListener != null) {
            this.mListener = imageListener;
        }
    }

    public int getSerialNum() {
        return mSerialNum;
    }

    public void setSerialNum(int serialNum) {
        mSerialNum = serialNum;
    }

    public ImageView getImageView() {
        if (mImageViewSoft != null) {
            return mImageViewSoft.get();
        }
        return null;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getImageUrlMD5() {
        return mImageUrlMD5;
    }

    public ImageListener getListener() {
        return mListener;
    }

    public DisplayConfig getConfig() {
        return mConfig;
    }

    public LoadPolicy getLoadPolicy() {
        return mLoadPolicy;
    }

    @Override
    public int compareTo(@NonNull BitmapRequest o) {
        return mLoadPolicy.compareTo(this, o);
    }
}
