package com.solarexsoft.simpleimageloader.config;

import com.solarexsoft.simpleimageloader.cache.BitmapCache;
import com.solarexsoft.simpleimageloader.loadpolicy.LoadPolicy;

/**
 * <pre>
 *    Author: houruhou
 *    Project: https://solarex.github.io/projects
 *    CreatAt: 12/06/2017
 *    Desc:
 * </pre>
 */

public class ImageLoaderConfig {
    private BitmapCache mCache;
    private LoadPolicy mLoadPolicy;
    private DisplayConfig mConfig;
    private int mThreadCount;

    private ImageLoaderConfig(Builder builder) {
        this.mCache = builder.cache;
        this.mLoadPolicy = builder.loadPolicy;
        this.mConfig = builder.config;
        this.mThreadCount = builder.threadCount;
    }

    public BitmapCache getCache() {
        return mCache;
    }

    public LoadPolicy getLoadPolicy() {
        return mLoadPolicy;
    }

    public DisplayConfig getConfig() {
        return mConfig;
    }

    public int getThreadCount() {
        return mThreadCount;
    }

    public static class Builder {
        private BitmapCache cache;
        private LoadPolicy loadPolicy;
        private DisplayConfig config;
        private int threadCount;

        public Builder() {
            this.config = new DisplayConfig();
        }

        public Builder setThreadCount(int threadCount) {
            this.threadCount = threadCount;
            return this;
        }

        public Builder setBitmapCache(BitmapCache cache) {
            this.cache = cache;
            return this;
        }

        public Builder setLoadPolicy(LoadPolicy loadPolicy) {
            this.loadPolicy = loadPolicy;
            return this;
        }

        public Builder setLoadingResource(int resId) {
            this.config.loadingImageResource = resId;
            return this;
        }

        public Builder setFailedResource(int resId) {
            this.config.failedImageResource = resId;
            return this;
        }

        public ImageLoaderConfig build() {
            return new ImageLoaderConfig(this);
        }
    }
}
