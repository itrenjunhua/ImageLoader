package com.renj.glide;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestOptions;
import com.renj.glide.transform.RotateTransformation;
import com.renj.glide.transform.RoundTransformation;
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
    @DrawableRes
    private int loadingRes;
    @DrawableRes
    private int errorRes;

    @Override
    public void init(@NonNull Application application) {
        this.application = application;
    }

    @Override
    public void init(@NonNull Application application, int loadingRes, int errorRes) {
        this.application = application;
        this.loadingRes = loadingRes;
        this.errorRes = errorRes;
    }

    @Override
    public void loadImage(@NonNull String url, @NonNull ImageView imageView) {
        ImageInfoConfig imageInfoConfig = new ImageInfoConfig.Builder()
                .url(url)
                .target(imageView)
                .build();
        loadImage(imageInfoConfig);
    }

    @Override
    public <T extends ImageInfoConfig> void loadImage(@NonNull T imageInfoConfig) {
        RequestManager requestManager = createRequestManager(imageInfoConfig);

        if (imageInfoConfig.isBitmap()) {
            RequestBuilder<Bitmap> bitmapRequestBuilder = requestManager.asBitmap();
            RequestBuilder<Bitmap> requestBuilder = loadPath(bitmapRequestBuilder, imageInfoConfig);
            builderControl(requestBuilder, imageInfoConfig);
        } else if (imageInfoConfig.isGif()) {
            RequestBuilder<GifDrawable> gifDrawableRequestBuilder = requestManager.asGif();
            RequestBuilder<GifDrawable> requestBuilder = loadPath(gifDrawableRequestBuilder, imageInfoConfig);
            builderControl(requestBuilder, imageInfoConfig);
        } else {
            RequestBuilder<Drawable> drawableRequestBuilder = requestManager.asDrawable();
            RequestBuilder<Drawable> requestBuilder = loadPath(drawableRequestBuilder, imageInfoConfig);
            builderControl(requestBuilder, imageInfoConfig);
        }
    }

    private <T> void builderControl(RequestBuilder<T> requestBuilder, @NonNull ImageInfoConfig imageInfoConfig) {
        requestBuilder = initImageInfoConfig(requestBuilder, imageInfoConfig);
        intoOf(requestBuilder, imageInfoConfig);
    }

    /**
     * 加载图片到指定控件
     */
    private <T> void intoOf(RequestBuilder<T> requestBuilder, @NonNull ImageInfoConfig imageInfoConfig) {
        if (imageInfoConfig.getTarget() instanceof ImageView) {
            requestBuilder.into((ImageView) imageInfoConfig.getTarget());
        }
    }

    /**
     * 配置图片信息到 Glide 请求中
     */
    @NonNull
    private <T> RequestBuilder<T> initImageInfoConfig(RequestBuilder<T> requestBuilder, @NonNull ImageInfoConfig imageInfoConfig) {
        RequestOptions requestOptions = new RequestOptions();
        if (imageInfoConfig.getWidth() > 0 && imageInfoConfig.getHeight() > 0)
            requestOptions = requestOptions.override(imageInfoConfig.getWidth(), imageInfoConfig.getHeight());
        if (imageInfoConfig.getWidth() > 0 && imageInfoConfig.getHeight() <= 0)
            requestOptions = requestOptions.override(imageInfoConfig.getWidth());
        if (imageInfoConfig.getHeight() > 0 && imageInfoConfig.getWidth() <= 0)
            requestOptions = requestOptions.override(imageInfoConfig.getHeight());

        requestOptions = requestOptions.skipMemoryCache(imageInfoConfig.isSkipMemory());
        requestOptions = requestOptions.diskCacheStrategy(imageInfoConfig.isSkipDisk() ? DiskCacheStrategy.NONE : DiskCacheStrategy.AUTOMATIC);

        if (loadingRes > 0)
            requestOptions = requestOptions.error(loadingRes);
        if (errorRes > 0)
            requestOptions = requestOptions.placeholder(errorRes);

        if (imageInfoConfig.getErrorImageId() > 0)
            requestOptions = requestOptions.error(imageInfoConfig.getErrorImageId());
        if (imageInfoConfig.getLoadingImageId() > 0)
            requestOptions = requestOptions.placeholder(imageInfoConfig.getLoadingImageId());

        if (imageInfoConfig.getErrorDrawable() != null)
            requestOptions = requestOptions.error(imageInfoConfig.getErrorDrawable());
        if (imageInfoConfig.getLoadingDrawable() != null)
            requestOptions = requestOptions.placeholder(imageInfoConfig.getLoadingDrawable());

        if (imageInfoConfig.isCenterCrop())
            requestOptions = requestOptions.centerCrop();
        if (imageInfoConfig.isFitCenter())
            requestOptions = requestOptions.fitCenter();
        if (imageInfoConfig.isCenterInside())
            requestOptions = requestOptions.centerInside();
        if (imageInfoConfig.getRotateConfig() != null)
            requestOptions = requestOptions.transform(new RotateTransformation(imageInfoConfig.getRotateConfig().rotateRotationAngle, imageInfoConfig.getRotateConfig().pivotX, imageInfoConfig.getRotateConfig().pivotY));
        if (imageInfoConfig.getRoundConfig() != null)
            requestOptions = requestOptions.transform(new RoundTransformation(imageInfoConfig.getRoundConfig().radiusX, imageInfoConfig.getRoundConfig().radiusY));

        if (imageInfoConfig.getThumbnail() > 0)
            requestBuilder.thumbnail(imageInfoConfig.getThumbnail());

        return requestBuilder.apply(requestOptions);
    }

    /**
     * 确定图片加载路径
     */
    @NonNull
    private <T> RequestBuilder<T> loadPath(RequestBuilder<T> requestBuilder, @NonNull ImageInfoConfig imageInfoConfig) {
        if (imageInfoConfig.getDrawableId() > 0)
            return requestBuilder.load(imageInfoConfig.getDrawableId());

        if (imageInfoConfig.getUri() != null)
            return requestBuilder.load(imageInfoConfig.getUri());

        if (imageInfoConfig.getFilePath() != null)
            return requestBuilder.load(imageInfoConfig.getFilePath());

        if (imageInfoConfig.getFile() != null)
            return requestBuilder.load(imageInfoConfig.getFile());

        // imageInfoConfig.getUrl() 也可能为 null
        return requestBuilder.load(imageInfoConfig.getUrl());
    }

    /**
     * 创建 RequestManager 对象
     */
    @NonNull
    private RequestManager createRequestManager(@NonNull ImageInfoConfig imageInfoConfig) {
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
