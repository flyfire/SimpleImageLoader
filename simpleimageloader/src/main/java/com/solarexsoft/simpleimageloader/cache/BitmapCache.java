package com.solarexsoft.simpleimageloader.cache;

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

public interface BitmapCache {
    void put(BitmapRequest request, Bitmap bitmap);
    Bitmap get(BitmapRequest request);
    void remove(BitmapRequest request);
}
