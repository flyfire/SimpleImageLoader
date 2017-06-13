package com.solarexsoft.simpleimageloader.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.solarexsoft.simpleimageloader.core.BitmapRequest;
import com.solarexsoft.simpleimageloader.utils.BitmapDecoder;
import com.solarexsoft.simpleimageloader.utils.IOUtils;
import com.solarexsoft.simpleimageloader.utils.ImageViewHelper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * <pre>
 *    Author: houruhou
 *    Project: https://solarex.github.io/projects
 *    CreatAt: 13/06/2017
 *    Desc:
 * </pre>
 */

public class NetworkLoader extends AbstractLoader {
    @Override
    protected Bitmap onLoad(BitmapRequest request) {
        HttpURLConnection connection = null;
        final InputStream inputStream;
        BitmapDecoder decoder = null;
        try{
            connection = (HttpURLConnection)(new URL(request.getImageUrl()).openConnection());
            inputStream = new BufferedInputStream(connection.getInputStream());
            inputStream.mark(inputStream.available());
            decoder = new BitmapDecoder() {
                @Override
                protected Bitmap decodeBitmapWithOptions(BitmapFactory.Options options) {
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
                    if (options.inJustDecodeBounds){
                        try {
                            inputStream.reset();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        IOUtils.closeQuietly(inputStream);
                    }
                    return bitmap;
                }
            };
            return decoder.decodeBitmap(ImageViewHelper.getImageViewWidth(request.getImageView()),
                    ImageViewHelper.getImageViewHeight(request.getImageView()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
