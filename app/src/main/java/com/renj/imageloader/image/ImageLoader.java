package com.renj.imageloader.image;

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
    private static IImageLoaderModule iImageLoaderModule;

    private ImageLoader() {
    }

    @org.jetbrains.annotations.Contract("null -> fail")
    public static void initImageLoader(IImageLoaderModule iImageLoaderModule) {
        if (iImageLoaderModule == null)
            throw new NullPointerException("initImageLoader() 方法参数不能为 null");

        ImageLoader.iImageLoaderModule = iImageLoaderModule;
        iImageLoaderModule.init();
    }

    @org.jetbrains.annotations.Contract(pure = true)
    public static IImageLoaderModule getImageLoaderModule() {
        if (iImageLoaderModule == null)
            throw new IllegalStateException("没有调用 ImageLoader.initImageLoader(IImageLoaderModule) 方法进行初始化");

        return ImageLoader.iImageLoaderModule;
    }
}
