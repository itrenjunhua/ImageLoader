package com.renj.imageloaderlibrary.picasso;

import com.renj.imageloaderlibrary.loader.IImageLoaderModule;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * 邮箱：renjunhua@anlovek.com
 * <p>
 * 创建时间：2018-04-27   17:32
 * <p>
 * 描述：Picasso加载框架扩展方法
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public interface IPicassoLoaderModule extends IImageLoaderModule {
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
}
