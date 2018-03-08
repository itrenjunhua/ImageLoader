package com.renj.imageloader.image;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.request.RequestOptions;

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
    // 磁盘缓存大小 50M
    public static final int DISK_CACHE_SIZE = 50 * 1024 * 1024;
    // 内存缓存
    public static final long MEMORY_CACHE_SIZE = (Runtime.getRuntime().maxMemory() / 8);
    // 缓存目录
    public static final String DISK_CACHE_NAME = "glide_cache";
    private Glide glide;

    @Override
    public void init(Application application) {
        RequestOptions options = new RequestOptions()
                .formatOf(DecodeFormat.PREFER_ARGB_8888);

        glide = new GlideBuilder()
                .setMemoryCache(new LruResourceCache(MEMORY_CACHE_SIZE))
                .setDiskCache(new InternalCacheDiskCacheFactory(application, DISK_CACHE_NAME, DISK_CACHE_SIZE))
                .setDefaultRequestOptions(options)
                .build(application);
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

        if (imageInfoConfig.getDrawableId() > 0)
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
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 必须在子线程中调用
                glide.clearDiskCache();
            }
        });
        thread.start();
    }
}
