package com.solarexsoft.simpleimageloader.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.solarexsoft.simpleimageloader.core.BitmapRequest;

/**
 * <pre>
 *    Author: houruhou
 *    Project: https://solarex.github.io/projects
 *    CreatAt: 12/06/2017
 *    Desc:
 * </pre>
 */

public class MemoryCache implements BitmapCache {

    private LruCache<String, Bitmap> mLruCache;

    public MemoryCache() {
        final int cacheSize = (int) (Runtime.getRuntime().maxMemory() / 1024 / 8);
        mLruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    @Override
    public void put(BitmapRequest request, Bitmap bitmap) {
        mLruCache.put(request.getImageUrlMD5(), bitmap);
    }

    @Override
    public Bitmap get(BitmapRequest request) {
        return mLruCache.get(request.getImageUrlMD5());
    }

    @Override
    public void remove(BitmapRequest request) {
        mLruCache.remove(request.getImageUrlMD5());
    }
}
