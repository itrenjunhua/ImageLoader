package com.renj.imageloader.image;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

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
     */
    void init();

    /**
     * 从网络中加载图片
     *
     * @param view            显示图片的控件
     * @param imageInfoConfig 图片信息
     */
    void loadImage(@NonNull View view, @Nullable ImageInfoConfig imageInfoConfig);

    /**
     * 清除内存缓存
     */
    void clearMemoryCache();

    /**
     * 清除磁盘缓存
     */
    void clearDiskCache();
}
