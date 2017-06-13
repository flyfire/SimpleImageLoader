package com.solarexsoft.simpleimageloader.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.solarexsoft.simpleimageloader.core.BitmapRequest;
import com.solarexsoft.simpleimageloader.utils.BitmapDecoder;
import com.solarexsoft.simpleimageloader.utils.ImageViewHelper;

import java.io.File;

/**
 * <pre>
 *    Author: houruhou
 *    Project: https://solarex.github.io/projects
 *    CreatAt: 13/06/2017
 *    Desc:
 * </pre>
 */

public class LocalLoader extends AbstractLoader {
    @Override
    protected Bitmap onLoad(BitmapRequest request) {
        final String path = Uri.parse(request.getImageUrl()).getPath();
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        BitmapDecoder decoder = new BitmapDecoder() {
            @Override
            protected Bitmap decodeBitmapWithOptions(BitmapFactory.Options options) {
                return BitmapFactory.decodeFile(path, options);
            }
        };
        return decoder.decodeBitmap(ImageViewHelper.getImageViewWidth(request.getImageView()),
                ImageViewHelper.getImageViewHeight(request.getImageView()));
    }
}
