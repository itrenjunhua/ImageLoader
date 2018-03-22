package com.renj.imageloader;

import android.app.Application;

import com.renj.imageloaderlibrary.GlideLoaderModule;
import com.renj.imageloaderlibrary.loader.ImageLoader;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2018-03-08   15:29
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ImageLoader.initImageLoader(this,new GlideLoaderModule());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        ImageLoader.getImageLoaderModule().clearAllMemoryCaches();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        ImageLoader.getImageLoaderModule().trimMemory(level);
    }
}
