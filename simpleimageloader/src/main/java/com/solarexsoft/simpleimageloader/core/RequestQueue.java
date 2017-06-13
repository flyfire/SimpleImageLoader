package com.solarexsoft.simpleimageloader.core;

import com.solarexsoft.simpleimageloader.utils.L;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 *    Author: houruhou
 *    Project: https://solarex.github.io/projects
 *    CreatAt: 12/06/2017
 *    Desc:
 * </pre>
 */

public class RequestQueue {
    private BlockingQueue<BitmapRequest> mRequestQueue = new PriorityBlockingQueue<>();
    private int mThreadCount;
    public static int DEFAULT_THREAD_COUNT = Runtime.getRuntime().availableProcessors();
    private AtomicInteger mAtomicInteger = new AtomicInteger(0);
    private RequestDispatcher[] mDispatchers;

    public RequestQueue(int threadCount) {
        this.mThreadCount = threadCount;
    }

    public void addRequest(BitmapRequest request) {
        if (!mRequestQueue.contains(request)) {
            request.setSerialNum(mAtomicInteger.getAndIncrement());
            mRequestQueue.add(request);
        } else {
            L.d("RequestQueue", "request: " + request + " already added");
        }
    }

    public void start() {
        stop();
        startDispatchers();
    }

    private void stop() {
        if (mDispatchers != null && mDispatchers.length > 0) {
            for (int i = 0; i < mDispatchers.length; i++) {
                mDispatchers[i].quit();
                mDispatchers[i] = null;
            }
            mDispatchers = null;
        }
    }

    private void startDispatchers() {
        mDispatchers = new RequestDispatcher[mThreadCount];
        for (int i = 0; i < mThreadCount; i++) {
            RequestDispatcher dispacher = new RequestDispatcher(mRequestQueue);
            mDispatchers[i] = dispacher;
            dispacher.start();
        }
    }
}
