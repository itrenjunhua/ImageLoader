package com.renj.imageloaderlibrary.loader;

import android.app.Application;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import com.renj.imageloaderlibrary.utils.Utils;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2018-03-09   15:11
 * <p>
 * 描述：LoaderModule 帮助类
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
/*public*/ class ImageLoaderHelper<T extends IImageLoaderModule> {
    private Application application;
    private T iImageLoaderModule;

    /**
     * 初始化方法
     *
     * @param application        {@link Application} 对象
     * @param iImageLoaderModule {@link IImageLoaderModule} 对象
     */
    void initImageLoaderUtils(@NonNull Application application, @NonNull T iImageLoaderModule) {
        this.application = application;
        this.iImageLoaderModule = iImageLoaderModule;

        Utils.initUtils(application);
        iImageLoaderModule.init(application);
    }

    /**
     * 初始化方法，指定全局的加载中图片和加载错误图片
     *
     * @param application        {@link Application} 对象
     * @param iImageLoaderModule {@link IImageLoaderModule} 对象
     * @param loadingRes         加载中的图片
     * @param errorRes           加载错误的图片
     */
    void initImageLoaderUtils(@NonNull Application application, @NonNull T iImageLoaderModule,
                              @DrawableRes int loadingRes, @DrawableRes int errorRes) {
        this.application = application;
        this.iImageLoaderModule = iImageLoaderModule;

        Utils.initUtils(application);
        iImageLoaderModule.init(application, loadingRes, errorRes);
    }

    /**
     * 获取 {@link Application} 对象
     *
     * @return {@link Application} 对象
     */
    @org.jetbrains.annotations.Contract(pure = true)
    Application getApplication() {
        return application;
    }

    /**
     * 获取 {@link IImageLoaderModule} 子类对象
     *
     * @param <T> {@link IImageLoaderModule} 子类对象
     * @return {@link IImageLoaderModule} 子类对象
     */
    @org.jetbrains.annotations.Contract(pure = true)
    <T extends IImageLoaderModule> T getImageLoaderModule() {
        return (T) iImageLoaderModule;
    }
}
