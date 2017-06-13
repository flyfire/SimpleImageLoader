package com.solarexsoft.simpleimageloader.loader;

import android.graphics.Bitmap;

import com.solarexsoft.simpleimageloader.core.BitmapRequest;

/**
 * <pre>
 *    Author: houruhou
 *    Project: https://solarex.github.io/projects
 *    CreatAt: 13/06/2017
 *    Desc:
 * </pre>
 */

public class NullLoader extends AbstractLoader {
    @Override
    protected Bitmap onLoad(BitmapRequest request) {
        return null;
    }
}
