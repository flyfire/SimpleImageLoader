package com.solarexsoft.simpleimageloader.cache;

import android.content.Context;
import android.graphics.Bitmap;

import com.solarexsoft.simpleimageloader.core.BitmapRequest;

/**
 * <pre>
 *    Author: houruhou
 *    Project: https://solarex.github.io/projects
 *    CreatAt: 12/06/2017
 *    Desc:
 * </pre>
 */

public class DoubleCache implements BitmapCache {
    private MemoryCache mMemoryCache = new MemoryCache();
    private DiskCache mDiskCache;

    public DoubleCache(Context context, String path) {
        mDiskCache = DiskCache.getInstance(context, path);
    }

    @Override
    public void put(BitmapRequest request, Bitmap bitmap) {
        mMemoryCache.put(request, bitmap);
        mDiskCache.put(request, bitmap);
    }

    @Override
    public Bitmap get(BitmapRequest request) {
        Bitmap bitmap = null;
        bitmap = mMemoryCache.get(request);
        if (bitmap == null) {
            bitmap = mDiskCache.get(request);
            if (bitmap != null) {
                mMemoryCache.put(request, bitmap);
                return bitmap;
            }
        }
        return null;
    }

    @Override
    public void remove(BitmapRequest request) {
        mMemoryCache.remove(request);
        mDiskCache.remove(request);
    }
}
