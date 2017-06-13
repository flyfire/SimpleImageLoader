package com.solarexsoft.simpleimageloader.loader;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *    Author: houruhou
 *    Project: https://solarex.github.io/projects
 *    CreatAt: 13/06/2017
 *    Desc:
 * </pre>
 */

public class LoaderManager {
    private Map<String, Loader> mLoaderMap = new HashMap<>();
    private static LoaderManager sManager = new LoaderManager();

    private LoaderManager() {
        register("http", new NetworkLoader());
        register("https", new NetworkLoader());
        register("file", new LocalLoader());
    }

    public static LoaderManager getInstance() {
        return sManager;
    }

    private void register(String schema, Loader loader) {
        mLoaderMap.put(schema, loader);
    }

    public Loader getLoader(String schema) {
        if (mLoaderMap.containsKey(schema)){
            return mLoaderMap.get(schema);
        }
        return new NullLoader();
    }
}
