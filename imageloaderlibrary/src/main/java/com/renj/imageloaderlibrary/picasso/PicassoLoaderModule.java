package com.renj.imageloaderlibrary.picasso;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.renj.imageloaderlibrary.loader.IImageLoaderModule;
import com.renj.imageloaderlibrary.loader.ImageInfoConfig;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * 邮箱：renjunhua@anlovek.com
 * <p>
 * 创建时间：2018-04-25   15:51
 * <p>
 * 描述：使用Picasso框架加载图片
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class PicassoLoaderModule implements IImageLoaderModule {
    private Application application;

    @Override
    public void init(Application application) {
        this.application = application;
    }

    @Override
    public void loadImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView) {
    }

    @Override
    public void loadImage(@NonNull ImageInfoConfig imageInfoConfig) {

    }
}
