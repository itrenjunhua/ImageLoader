package com.renj.imageloader.image.loader;

import android.app.Application;
import android.support.annotation.NonNull;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2018-03-08   10:47
 * <p>
 * 描述：使用二次封装后的图片框架入口类
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class ImageLoader {
    private ImageLoader() {
    }

    @org.jetbrains.annotations.Contract("null,null -> fail; _,null -> fail; null,_ -> fail")
    public static void initImageLoader(@NonNull Application application, @NonNull IImageLoaderModule iImageLoaderModule) {
        if (iImageLoaderModule == null || application == null)
            throw new NullPointerException("initImageLoader() 方法参数不能为 null");

        ImageLoaderUitls.init(application, iImageLoaderModule);
    }

    @org.jetbrains.annotations.Contract(pure = true)
    public static IImageLoaderModule getImageLoaderModule() {
        if (ImageLoaderUitls.getImageLoaderModule() == null)
            throw new IllegalStateException("没有调用 ImageLoader.initImageLoader(IImageLoaderModule) 方法进行初始化");

        return ImageLoaderUitls.getImageLoaderModule();
    }
}
