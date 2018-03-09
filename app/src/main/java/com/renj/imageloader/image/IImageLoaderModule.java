package com.renj.imageloader.image;

import android.app.Application;
import android.support.annotation.NonNull;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2018-03-08   10:30
 * <p>
 * 描述：加载接口，由子类实现。子类使用具体的框架实现
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public interface IImageLoaderModule {
    /**
     * 对框架进行初始化，参数必须为 {@link Application} ，强制在 Application 类中初始化
     *
     * @param application {@link Application}
     */
    void init(Application application);

    /**
     * 从网络中加载图片
     *
     * @param imageInfoConfig {@link ImageInfoConfig} 对象
     */
    void loadImage(@NonNull ImageInfoConfig imageInfoConfig);

    /**
     * 暂停加载
     */
    void pause();

    /**
     * 开始加载
     */
    void resume();

    /**
     * 清除内存缓存
     */
    void clearMemoryCache();

    /**
     * 根据级别清除内存中的缓存
     *
     * @param level 需要清除的级别
     */
    void trimMemory(int level);

    /**
     * 清除所有内存缓存
     */
    void clearAllMemoryCaches();

    /**
     * 清除磁盘缓存
     */
    void clearDiskCache();
}
