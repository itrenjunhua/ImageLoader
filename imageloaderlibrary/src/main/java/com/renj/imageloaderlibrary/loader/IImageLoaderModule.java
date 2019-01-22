package com.renj.imageloaderlibrary.loader;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.renj.imageloaderlibrary.config.ImageLoadConfig;
import com.renj.imageloaderlibrary.config.ImageModuleConfig;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2018-03-08   10:30
 * <p>
 * 描述：图片加载接口顶级类，定义最基本的图片加载方法，可扩展。由子类实现。子类使用具体的框架实现
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public interface IImageLoaderModule {
    /**
     * 对框架进行初始化
     *
     * @param imageModuleConfig {@link ImageModuleConfig}
     */
    void init(@NonNull ImageModuleConfig imageModuleConfig);

    /**
     * 简单的加载网络图片
     *
     * @param url       图片路径
     * @param imageView {@link ImageView} 控件
     */
    void loadImage(@NonNull String url, @NonNull ImageView imageView);

    /**
     * 根据配置信息加载图片
     *
     * @param imageInfoConfig {@code T extends ImageLoadConfig} 对象
     * @param <T>             {@code T extends ImageLoadConfig}
     */
    <T extends ImageLoadConfig> void loadImage(@NonNull T imageInfoConfig);

    /**
     * 暂停加载
     */
    void pause();

    /**
     * 开始加载
     */
    void resume();

    /**
     * 暂停加载指定 Tag
     *
     * @param tag 暂停加载的tag
     */
    void pauseTag(Object tag);

    /**
     * 开始加载指定 Tag
     *
     * @param tag 开始加载的tag
     */
    void resumeTag(Object tag);

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
