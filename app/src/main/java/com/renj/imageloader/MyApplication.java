package com.renj.imageloader;

import android.app.Application;

import com.renj.imageloader.image.GlideLoaderModule;
import com.renj.imageloader.image.ImageLoader;

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
}
