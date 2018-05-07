package com.renj.imageloader;

import android.app.Application;
import android.support.annotation.NonNull;

import com.renj.imageloaderlibrary.GlideLoaderModule;
import com.renj.imageloaderlibrary.loader.ImageLoaderModule;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * 邮箱：renjunhua@anlovek.com
 * <p>
 * 创建时间：2018-05-07   16:29
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class ImageLoaderManager {
    public static void init(@NonNull Application application) {
        ImageLoaderModule.initImageLoaderModule(application, new GlideLoaderModule());
    }

    public static GlideLoaderModule getImageLoader() {
        return ImageLoaderModule.getImageLoaderModule();
    }
}
