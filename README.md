# SimpleImageLoader
简单的图片加载框架

[ ![Download](https://api.bintray.com/packages/solarexsoft/maven/SimpleImageLoader/images/download.svg) ](https://bintray.com/solarexsoft/maven/SimpleImageLoader/_latestVersion)

```
compile 'com.solarexsoft.simpleimageloader:simpleimageloader:1.0.0'
```

Usage:

```
        ImageLoaderConfig config = new ImageLoaderConfig.Builder()              .setThreadCount(3)
                .setBitmapCache(new DoubleCache(context, "solarex"))
                //.setBitmapCache(new MemoryCache())
                .setLoadPolicy(new ReversePolicy())
                .setLoadingResource(R.drawable.loading)
                .setFailedResource(R.drawable.not_found)
                .build();
        mLoader = SimpleImageLoader.getInstance(config);
        mLoader.displayImage(imageView, Constants.imageThumbUrls[position]);
```


