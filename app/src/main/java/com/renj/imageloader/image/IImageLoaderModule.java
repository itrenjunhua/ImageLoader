package com.renj.imageloader.image;

import android.content.Context;
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
     * 对框架进行初始化
     *
     * @param context 上下文
     */
    void init(Context context);

    /**
     * 从网络中加载图片
     *
     * @param imageInfoConfig {@link ImageInfoConfig} 对象
     */
    void loadImage(@NonNull ImageInfoConfig imageInfoConfig);

    /**
     * 清除内存缓存
     */
    void clearMemoryCache();

    /**
     * 清除磁盘缓存
     */
    void clearDiskCache();
}
