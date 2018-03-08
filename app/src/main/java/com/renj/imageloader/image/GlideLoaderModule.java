package com.renj.imageloader.image;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2018-03-08   15:06
 * <p>
 * 描述：使用Glide框架加载图片
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class GlideLoaderModule implements IImageLoaderModule {
    private Glide glide;

    @Override
    public void init(Context context) {
        glide = Glide.get(context);
    }

    @Override
    public void loadImage(@NonNull final ImageInfoConfig imageInfoConfig) {
        RequestBuilder<Drawable> load = Glide.with(imageInfoConfig.getContext())
                .load(imageInfoConfig.getUrl());
        if (imageInfoConfig.getTarget() instanceof ImageView) {
            load.into((ImageView) imageInfoConfig.getTarget());

        }
    }

    @Override
    public void clearMemoryCache() {
        glide.clearMemory();
    }

    @Override
    public void clearDiskCache() {
        glide.clearDiskCache();
    }
}
