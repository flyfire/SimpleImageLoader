package com.solarexsoft.simpleimageloader.core;

import com.solarexsoft.simpleimageloader.loader.Loader;
import com.solarexsoft.simpleimageloader.loader.LoaderManager;
import com.solarexsoft.simpleimageloader.utils.L;

import java.util.concurrent.BlockingQueue;

/**
 * <pre>
 *    Author: houruhou
 *    Project: https://solarex.github.io/projects
 *    CreatAt: 12/06/2017
 *    Desc:
 * </pre>
 */

public class RequestDispatcher extends Thread {
    private boolean isShouldStop = false;
    private BlockingQueue<BitmapRequest> mRequestQueue;

    public RequestDispatcher(BlockingQueue<BitmapRequest> requestQueue) {
        mRequestQueue = requestQueue;
    }

    @Override
    public void run() {
        while (!isShouldStop && !isInterrupted()) {
            try {
                BitmapRequest request = mRequestQueue.take();
                String schema = parseSchema(request.getImageUrl());
                Loader loader = LoaderManager.getInstance().getLoader(schema);
                loader.loadImage(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private String parseSchema(String imageUrl) {
        if (imageUrl.contains("://")) {
            return imageUrl.split("://")[0];
        } else {
            L.d("RequestDispatcher", "unsupported schema");
        }
        return null;
    }

    public void quit(){
        isShouldStop = true;
        interrupt();
    }
}
