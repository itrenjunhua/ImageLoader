package com.renj.imageloader.image;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.gif.GifDrawable;

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
    private Application application;

    @Override
    public void init(Application application) {
        this.application = application;
    }

    @Override
    public void loadImage(@NonNull ImageInfoConfig imageInfoConfig) {
        RequestManager requestManager = createRequestManager(imageInfoConfig);

        RequestBuilder requestBuilder;

        if (imageInfoConfig.isBitmap()) {
            RequestBuilder<Bitmap> bitmapRequestBuilder = requestManager.asBitmap();
            requestBuilder = loadPath(bitmapRequestBuilder, imageInfoConfig);
        } else if (imageInfoConfig.isGif()) {
            RequestBuilder<GifDrawable> gifDrawableRequestBuilder = requestManager.asGif();
            requestBuilder = loadPath(gifDrawableRequestBuilder, imageInfoConfig);
        } else {
            RequestBuilder<Drawable> drawableRequestBuilder = requestManager.asDrawable();
            requestBuilder = loadPath(drawableRequestBuilder, imageInfoConfig);
        }

        if (imageInfoConfig.getTarget() instanceof ImageView) {
            requestBuilder.into((ImageView) imageInfoConfig.getTarget());
        }
    }

    @NonNull
    private <T> RequestBuilder<T> loadPath(RequestBuilder<T> requestBuilder, ImageInfoConfig imageInfoConfig) {
        if (imageInfoConfig.getDrawable() != null)
            return requestBuilder.load(imageInfoConfig.getDrawable());

        if (imageInfoConfig.getBitmap() != null)
            return requestBuilder.load(imageInfoConfig.getBitmap());

        if (imageInfoConfig.getDrawableId() > 0)
            return requestBuilder.load(imageInfoConfig.getDrawableId());

        if (imageInfoConfig.getBytes() != null)
            return requestBuilder.load(imageInfoConfig.getBytes());

        if (imageInfoConfig.getUri() != null)
            return requestBuilder.load(imageInfoConfig.getUri());

        if (imageInfoConfig.getFilePath() != null)
            return requestBuilder.load(imageInfoConfig.getFilePath());

        if (imageInfoConfig.getFile() != null)
            return requestBuilder.load(imageInfoConfig.getFile());

        // imageInfoConfig.getUrl() 也可能为 null
        return requestBuilder.load(imageInfoConfig.getUrl());
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
    public void pause() {
        Glide.with(application).pauseRequestsRecursive();
    }

    @Override
    public void resume() {
        Glide.with(application).resumeRequestsRecursive();
    }

    @Override
    public void clearMemoryCache() {
        Glide.get(application).clearMemory();
    }

    @Override
    public void trimMemory(int level) {
        Glide.get(application).onTrimMemory(level);
    }

    @Override
    public void clearAllMemoryCaches() {
        Glide.get(application).onLowMemory();
    }

    @Override
    public void clearDiskCache() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 必须在子线程中调用
                Glide.get(application).clearDiskCache();
            }
        });
        thread.start();
    }
}
