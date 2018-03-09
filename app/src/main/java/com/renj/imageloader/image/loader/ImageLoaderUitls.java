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

    @org.jetbrains.annotations.Contract(pure = true)
    static Application getApplication() {
        return application;
    }

    @org.jetbrains.annotations.Contract(pure = true)
    static IImageLoaderModule getImageLoaderModule() {
        return iImageLoaderModule;
    }

    @org.jetbrains.annotations.Contract(pure = true)
    static int getWinWidth() {
        return winWidth;
    }

    @org.jetbrains.annotations.Contract(pure = true)
    static int getWinHeight() {
        return winHeight;
    }
}
