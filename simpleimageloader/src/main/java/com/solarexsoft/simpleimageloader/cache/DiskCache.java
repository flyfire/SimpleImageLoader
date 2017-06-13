package com.solarexsoft.simpleimageloader.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.jakewharton.disklrucache.DiskLruCache;
import com.solarexsoft.simpleimageloader.core.BitmapRequest;
import com.solarexsoft.simpleimageloader.utils.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * <pre>
 *    Author: houruhou
 *    Project: https://solarex.github.io/projects
 *    CreatAt: 12/06/2017
 *    Desc:
 * </pre>
 */

public class DiskCache implements BitmapCache {
    private DiskLruCache mDiskLruCache;
    private static volatile DiskCache mDiskCache;

    public static DiskCache getInstance(Context context, String path) {
        if (mDiskCache == null) {
            synchronized (DiskCache.class) {
                if (mDiskCache == null) {
                    mDiskCache = new DiskCache(context, path);
                }
            }
        }
        return mDiskCache;
    }

    public static DiskCache getInstance() {
        if (mDiskCache == null) {
            throw new IllegalStateException("Use getInstance(Context,String) init first!");
        }
        return mDiskCache;
    }


    private DiskCache(Context context, String path) {
        //use context to get cache directory
        //use external disk to watch the file change
        File file = new File(Environment.getExternalStorageDirectory(), path);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            mDiskLruCache = DiskLruCache.open(file, 1, 1, 5 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void put(BitmapRequest request, Bitmap bitmap) {
        DiskLruCache.Editor editor = null;
        OutputStream os = null;
        try {
            editor = mDiskLruCache.edit(request.getImageUrlMD5());
            os = editor.newOutputStream(0);
            if (persistBitmap2Disk(bitmap, os)) {
                editor.commit();
            } else {
                editor.abort();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean persistBitmap2Disk(Bitmap bitmap, OutputStream os) {
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
        try {
            os.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(os);
        }
        return false;
    }

    @Override
    public Bitmap get(BitmapRequest request) {
        try {
            DiskLruCache.Snapshot snapshot = mDiskLruCache.get(request.getImageUrlMD5());
            if (snapshot != null) {
                InputStream is = snapshot.getInputStream(0);
                return BitmapFactory.decodeStream(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(BitmapRequest request) {
        try {
            mDiskLruCache.remove(request.getImageUrlMD5());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
































}
