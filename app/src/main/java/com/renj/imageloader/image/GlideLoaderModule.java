package com.renj.imageloader.image;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;

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
        RequestManager requestManager = createRequestManager(imageInfoConfig);

        RequestBuilder<Drawable> requestBuilder = loadPath(requestManager, imageInfoConfig);

        if (imageInfoConfig.getTarget() instanceof ImageView) {
            requestBuilder.into((ImageView) imageInfoConfig.getTarget());
        }
    }

    @NonNull
    private RequestBuilder<Drawable> loadPath(RequestManager requestManager, ImageInfoConfig imageInfoConfig) {
        if (imageInfoConfig.getDrawable() != null)
            return requestManager.load(imageInfoConfig.getDrawable());

        if (imageInfoConfig.getBitmap() != null)
            return requestManager.load(imageInfoConfig.getBitmap());

        if (imageInfoConfig.getDrawableId() != -1)
            return requestManager.load(imageInfoConfig.getDrawableId());

        if (imageInfoConfig.getBytes() != null)
            return requestManager.load(imageInfoConfig.getBytes());

        if (imageInfoConfig.getUri() != null)
            return requestManager.load(imageInfoConfig.getUri());

        if (imageInfoConfig.getFilePath() != null)
            return requestManager.load(imageInfoConfig.getFilePath());

        if (imageInfoConfig.getFile() != null)
            return requestManager.load(imageInfoConfig.getFile());

        // imageInfoConfig.getUrl() 也可能为 null
        return requestManager.load(imageInfoConfig.getUrl());
    }

    @NonNull
    private RequestManager createRequestManager(ImageInfoConfig imageInfoConfig) {
        if (imageInfoConfig.getFragmentV4() != null)
            return Glide.with(imageInfoConfig.getFragmentV4());

        if (imageInfoConfig.getFragment() != null)
            return Glide.with(imageInfoConfig.getFragment());

        if (imageInfoConfig.getFragmentActivity() != null)
            return Glide.with(imageInfoConfig.getFragmentActivity());

        if (imageInfoConfig.getActivity() != null)
            return Glide.with(imageInfoConfig.getActivity());

        if (imageInfoConfig.getContext() != null)
            return Glide.with(imageInfoConfig.getContext());

        if (imageInfoConfig.getTarget() != null)
            return Glide.with(imageInfoConfig.getTarget());

        throw new NullPointerException("Glide 获取不到 Context");
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
