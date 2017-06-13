package com.solarexsoft.simpleimageloaderdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.solarexsoft.simpleimageloader.cache.DoubleCache;
import com.solarexsoft.simpleimageloader.config.ImageLoaderConfig;
import com.solarexsoft.simpleimageloader.core.SimpleImageLoader;
import com.solarexsoft.simpleimageloader.loadpolicy.ReversePolicy;

/**
 * <pre>
 *    Author: houruhou
 *    Project: https://solarex.github.io/projects
 *    CreatAt: 13/06/2017
 *    Desc:
 * </pre>
 */

public class Adapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private SimpleImageLoader mLoader;

    public Adapter(Context context) {
        mInflater = LayoutInflater.from(context);
        ImageLoaderConfig config = new ImageLoaderConfig.Builder().setThreadCount(3)
                .setBitmapCache(new DoubleCache(context, "solarex"))
                //.setBitmapCache(new MemoryCache())
                .setLoadPolicy(new ReversePolicy())
                .setLoadingResource(R.drawable.loading)
                .setFailedResource(R.drawable.not_found)
                .build();
        mLoader = SimpleImageLoader.getInstance(config);
    }

    @Override
    public int getCount() {
        return Constants.imageThumbUrls.length;
    }

    @Override
    public Object getItem(int position) {
        return Constants.imageThumbUrls[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.item, parent, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        mLoader.displayImage(imageView, Constants.imageThumbUrls[position]);
        return view;
    }
}
