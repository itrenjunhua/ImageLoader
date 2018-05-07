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
public class ImageLoaderModule {
    public static ImageLoaderHelper imageLoaderHelper;

    private ImageLoaderModule() {
    }

    @org.jetbrains.annotations.Contract("null,null -> fail; _,null -> fail; null,_ -> fail")
    public static <T extends IImageLoaderModule> void initImageLoaderModule(@NonNull Application application, @NonNull T iImageLoaderModule) {
        if (imageLoaderHelper == null) {
            synchronized (ImageLoaderModule.class) {
                if (imageLoaderHelper == null) {
                    if (iImageLoaderModule == null || application == null)
                        throw new NullPointerException("initImageLoaderModule() 方法参数不能为 null");

                    imageLoaderHelper = new ImageLoaderHelper<T>();
                    imageLoaderHelper.initImageLoaderUtils(application, iImageLoaderModule);
                }
            }
        }
    }

    @org.jetbrains.annotations.Contract(pure = true)
    public static <T extends IImageLoaderModule> T getImageLoaderModule() {
        if (imageLoaderHelper == null || imageLoaderHelper.getImageLoaderModule() == null)
            throw new IllegalStateException("没有调用 ImageLoaderModule.initImageLoaderModule(IImageLoaderModule) 方法进行初始化");

        return (T) imageLoaderHelper.getImageLoaderModule();
    }
}
