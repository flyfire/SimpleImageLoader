package com.solarexsoft.simpleimageloader.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * <pre>
 *    Author: houruhou
 *    Project: https://solarex.github.io/projects
 *    CreatAt: 12/06/2017
 *    Desc:
 * </pre>
 */

public abstract class BitmapDecoder {
    public Bitmap decodeBitmap(int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        decodeBitmapWithOptions(options);
        calculateSampleSizeWithOptions(options, reqWidth, reqHeight);
        return decodeBitmapWithOptions(options);
    }

    private void calculateSampleSizeWithOptions(BitmapFactory.Options options, int reqWidth, int
            reqHeight) {
        int width = options.outWidth;
        int height = options.outHeight;
        int inSampleSize = 1;
        if (width > reqWidth || height > reqHeight) {
            int widthRatio = Math.round((float) width / (float) reqWidth);
            int heightRatio = Math.round((float) height / (float) reqHeight);

            inSampleSize = Math.max(widthRatio, heightRatio);
        }

        options.inSampleSize = inSampleSize;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inJustDecodeBounds = false;
        options.inPurgeable = true;
        options.inInputShareable = true;
    }

    protected abstract Bitmap decodeBitmapWithOptions(BitmapFactory.Options options);


}
