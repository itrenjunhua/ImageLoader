package com.renj.imageloader.image.loader;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.view.WindowManager;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2018-03-09   15:11
 * <p>
 * 描述：工具类
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
/*public*/ class ImageLoaderUitls {
    private static Application application;
    private static IImageLoaderModule iImageLoaderModule;
    private static int winWidth;
    private static int winHeight;

    /**
     * 初始化方法
     *
     * @param application        {@link Application} 对象
     * @param iImageLoaderModule {@link IImageLoaderModule} 对象
     */
    static void init(@NonNull Application application, @NonNull IImageLoaderModule iImageLoaderModule) {
        ImageLoaderUitls.application = application;
        ImageLoaderUitls.iImageLoaderModule = iImageLoaderModule;
        WindowManager wm = (WindowManager) application.getSystemService(Context.WINDOW_SERVICE);

        Point point = new Point();
        wm.getDefaultDisplay().getRealSize(point);
        ImageLoaderUitls.winWidth = point.x;
        ImageLoaderUitls.winHeight = point.y;

        iImageLoaderModule.init(application);
    }

    /**
     * 获取 {@link Application} 对象
     *
     * @return {@link Application} 对象
     */
    @org.jetbrains.annotations.Contract(pure = true)
    static Application getApplication() {
        return application;
    }

    /**
     * 获取 {@link IImageLoaderModule} 对象
     *
     * @return {@link IImageLoaderModule} 对象
     */
    @org.jetbrains.annotations.Contract(pure = true)
    static IImageLoaderModule getImageLoaderModule() {
        return iImageLoaderModule;
    }

    /**
     * 获取屏幕的宽
     *
     * @return 屏幕的宽
     */
    @org.jetbrains.annotations.Contract(pure = true)
    static int getWinWidth() {
        return winWidth;
    }

    /**
     * 获取屏幕的高
     *
     * @return 屏幕的高
     */
    @org.jetbrains.annotations.Contract(pure = true)
    static int getWinHeight() {
        return winHeight;
    }
}
