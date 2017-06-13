package com.solarexsoft.simpleimageloader.core;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * <pre>
 *    Author: houruhou
 *    Project: https://solarex.github.io/projects
 *    CreatAt: 12/06/2017
 *    Desc:
 * </pre>
 */

public interface ImageListener {
    void onComplete(ImageView imageView, Bitmap bitmap, String uri);
}
