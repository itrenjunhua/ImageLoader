package com.renj.imageloaderlibrary.glide;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestOptions;
import com.renj.imageloaderlibrary.loader.ImageInfoConfig;

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
public class GlideLoaderModule implements IGlideLoaderModule {
    private Application application;

    @Override
    public void init(@NonNull Application application) {
        this.application = application;
    }

    @Override
    public void loadImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView) {
        ImageInfoConfig imageInfoConfig = new GlideImageInfoConfig.Builder()
                .context(context)
                .url(url)
                .target(imageView)
                .build();
        loadImage(imageInfoConfig);
    }

    @Override
    public <T extends ImageInfoConfig> void loadImage(@NonNull T imageInfoConfig) {
        if (!(imageInfoConfig instanceof GlideImageInfoConfig))
            throw new IllegalArgumentException(getClass().getName() + "#loadImage(ImageInfoConfig) 方法的参数为" + GlideImageInfoConfig.class.getName());

        GlideImageInfoConfig glideImageInfoConfig = (GlideImageInfoConfig) imageInfoConfig;
        RequestManager requestManager = createRequestManager(glideImageInfoConfig);

        if (glideImageInfoConfig.isBitmap()) {
            RequestBuilder<Bitmap> bitmapRequestBuilder = requestManager.asBitmap();
            RequestBuilder<Bitmap> requestBuilder = loadPath(bitmapRequestBuilder, glideImageInfoConfig);
            builderControl(requestBuilder, glideImageInfoConfig);
        } else if (glideImageInfoConfig.isGif()) {
            RequestBuilder<GifDrawable> gifDrawableRequestBuilder = requestManager.asGif();
            RequestBuilder<GifDrawable> requestBuilder = loadPath(gifDrawableRequestBuilder, glideImageInfoConfig);
            builderControl(requestBuilder, glideImageInfoConfig);
        } else {
            RequestBuilder<Drawable> drawableRequestBuilder = requestManager.asDrawable();
            RequestBuilder<Drawable> requestBuilder = loadPath(drawableRequestBuilder, glideImageInfoConfig);
            builderControl(requestBuilder, glideImageInfoConfig);
        }
    }

    private <T> void builderControl(RequestBuilder<T> requestBuilder, @NonNull GlideImageInfoConfig glideImageInfoConfig) {
        requestBuilder = initImageInfoConfig(requestBuilder, glideImageInfoConfig);
        intoOf(requestBuilder, glideImageInfoConfig);
    }

    /**
     * 加载图片到指定控件
     */
    private <T> void intoOf(RequestBuilder<T> requestBuilder, @NonNull GlideImageInfoConfig glideImageInfoConfig) {
        if (glideImageInfoConfig.getTarget() instanceof ImageView) {
            requestBuilder.into((ImageView) glideImageInfoConfig.getTarget());
        }
    }

    /**
     * 配置图片信息到 Glide 请求中
     */
    @NonNull
    private <T> RequestBuilder<T> initImageInfoConfig(RequestBuilder<T> requestBuilder, @NonNull GlideImageInfoConfig glideImageInfoConfig) {
        if (glideImageInfoConfig.getThumbnail() > 0)
            requestBuilder.thumbnail(glideImageInfoConfig.getThumbnail());

        RequestOptions requestOptions = new RequestOptions();
        if (glideImageInfoConfig.getWidth() > 0 && glideImageInfoConfig.getHeight() > 0)
            requestOptions = requestOptions.override(glideImageInfoConfig.getWidth(), glideImageInfoConfig.getHeight());
        if (glideImageInfoConfig.getWidth() > 0 && glideImageInfoConfig.getHeight() <= 0)
            requestOptions = requestOptions.override(glideImageInfoConfig.getWidth());
        if (glideImageInfoConfig.getHeight() > 0 && glideImageInfoConfig.getWidth() <= 0)
            requestOptions = requestOptions.override(glideImageInfoConfig.getHeight());

        requestOptions = requestOptions.skipMemoryCache(glideImageInfoConfig.isSkipMemory());
        requestOptions = requestOptions.diskCacheStrategy(glideImageInfoConfig.isSkipDisk() ? DiskCacheStrategy.NONE : DiskCacheStrategy.AUTOMATIC);

        if (glideImageInfoConfig.getErrorImageId() > 0)
            requestOptions = requestOptions.error(glideImageInfoConfig.getErrorImageId());
        if (glideImageInfoConfig.getLoadingImageId() > 0)
            requestOptions = requestOptions.placeholder(glideImageInfoConfig.getLoadingImageId());

        return requestBuilder.apply(requestOptions);
    }

    /**
     * 确定图片加载路径
     */
    @NonNull
    private <T> RequestBuilder<T> loadPath(RequestBuilder<T> requestBuilder, @NonNull GlideImageInfoConfig glideImageInfoConfig) {
        if (glideImageInfoConfig.getDrawable() != null)
            return requestBuilder.load(glideImageInfoConfig.getDrawable());

        if (glideImageInfoConfig.getBitmap() != null)
            return requestBuilder.load(glideImageInfoConfig.getBitmap());

        if (glideImageInfoConfig.getDrawableId() > 0)
            return requestBuilder.load(glideImageInfoConfig.getDrawableId());

        if (glideImageInfoConfig.getBytes() != null)
            return requestBuilder.load(glideImageInfoConfig.getBytes());

        if (glideImageInfoConfig.getUri() != null)
            return requestBuilder.load(glideImageInfoConfig.getUri());

        if (glideImageInfoConfig.getFilePath() != null)
            return requestBuilder.load(glideImageInfoConfig.getFilePath());

        if (glideImageInfoConfig.getFile() != null)
            return requestBuilder.load(glideImageInfoConfig.getFile());

        // imageInfoConfig.getUrl() 也可能为 null
        return requestBuilder.load(glideImageInfoConfig.getUrl());
    }

    /**
     * 创建 RequestManager 对象
     */
    @NonNull
    private RequestManager createRequestManager(@NonNull GlideImageInfoConfig glideImageInfoConfig) {
        if (glideImageInfoConfig.getFragmentV4() != null)
            return Glide.with(glideImageInfoConfig.getFragmentV4());

        if (glideImageInfoConfig.getFragment() != null)
            return Glide.with(glideImageInfoConfig.getFragment());

        if (glideImageInfoConfig.getFragmentActivity() != null)
            return Glide.with(glideImageInfoConfig.getFragmentActivity());

        if (glideImageInfoConfig.getActivity() != null)
            return Glide.with(glideImageInfoConfig.getActivity());

        if (glideImageInfoConfig.getContext() != null)
            return Glide.with(glideImageInfoConfig.getContext());

        if (glideImageInfoConfig.getTarget() != null)
            return Glide.with(glideImageInfoConfig.getTarget());

        if (application != null)
            return Glide.with(application);

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
