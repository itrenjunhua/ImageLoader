package com.renj.imageloaderlibrary.loader;

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
    public static ImageLoaderUtils imageLoaderUtils;

    private ImageLoader() {
    }

    @org.jetbrains.annotations.Contract("null,null -> fail; _,null -> fail; null,_ -> fail")
    public static <T extends IImageLoaderModule> void initImageLoader(@NonNull Application application, @NonNull T iImageLoaderModule) {
        imageLoaderUtils = new ImageLoaderUtils<T>();

        if (iImageLoaderModule == null || application == null)
            throw new NullPointerException("initImageLoader() 方法参数不能为 null");

        imageLoaderUtils.init(application, iImageLoaderModule);
    }

    @org.jetbrains.annotations.Contract(pure = true)
    public static <T extends IImageLoaderModule> T getImageLoaderModule() {
        if (imageLoaderUtils == null || imageLoaderUtils.getImageLoaderModule() == null)
            throw new IllegalStateException("没有调用 ImageLoader.initImageLoader(IImageLoaderModule) 方法进行初始化");

        return (T) imageLoaderUtils.getImageLoaderModule();
    }
}
