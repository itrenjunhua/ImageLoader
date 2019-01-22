package com.renj.imageloader;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.renj.glide.GlideLoaderModule;
import com.renj.imageloaderlibrary.config.ImageLoadConfig;
import com.renj.imageloaderlibrary.config.ImageLoadLibrary;
import com.renj.imageloaderlibrary.config.ImageModuleConfig;
import com.renj.imageloaderlibrary.loader.ImageLoaderModule;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * 邮箱：renjunhua@anlovek.com
 * <p>
 * 创建时间：2018-05-07   16:29
 * <p>
 * 描述：图片加载管理类
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class ImageLoaderManager {
    /**
     * 初始化自定义图片加载框架
     *
     * @param application {@link Application} 对象
     */
    public static void init(@NonNull Application application) {
        ImageLoaderModule.initImageLoaderModule(
                new ImageModuleConfig.Builder(application)
                        .imageLoadModule(ImageLoadLibrary.GLIDE_LIBRARY, new GlideLoaderModule())
                        .build());
    }

    public static GlideLoaderModule getImageLoader() {
        return ImageLoaderModule.getDefaultImageLoaderModule();
    }

    public static void loadCircleImage(@NonNull Activity activity, @NonNull String url, @NonNull ImageView imageView) {
        ImageLoadConfig config = new ImageLoadConfig.Builder()
                .asBitmap()
                .thumbnail(0.2f)
                .context(activity)
                .url(url)
                .asCircle()
                .loadingImageId(R.mipmap.ic_launcher_round)
                .target(imageView)
                .build();
        ImageLoaderManager.getImageLoader().loadImage(config);
    }
}
