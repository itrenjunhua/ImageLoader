package com.renj.imageloader;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.renj.glide.GlideImageInfoConfig;
import com.renj.glide.GlideLoaderModule;
import com.renj.glide.transform.CircleTransformation;
import com.renj.imageloaderlibrary.loader.IImageLoaderModule;
import com.renj.imageloaderlibrary.loader.ImageInfoConfig;
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
        ImageLoaderModule.initImageLoaderModule(application, new GlideLoaderModule());
    }

    /**
     * 获取图片加载Module {@link com.renj.imageloaderlibrary.loader.IImageLoaderModule} 的子类对象。
     * <b>注意：这里返回的结果为{@link com.renj.imageloaderlibrary.loader.IImageLoaderModule} 子类对象，具体是哪一个根据在{@link #init(Application)} 方法中
     * 调用 {@link ImageLoaderModule#initImageLoaderModule(Application, IImageLoaderModule)} 方法传递的第二个参数确定。</b>
     *
     * @return 返回 {@link com.renj.imageloaderlibrary.loader.IImageLoaderModule} 子类对象
     */
    public static GlideLoaderModule getImageLoader() {
        return ImageLoaderModule.getImageLoaderModule();
    }

    public static void loadCircleImage(@NonNull Activity activity, @NonNull String url, @NonNull ImageView imageView) {
        ImageInfoConfig config = new GlideImageInfoConfig.Builder()
                .asBitmap()
                .thumbnail(0.2f)
                .transformation(new CircleTransformation())
                .context(activity)
                .url(url)
                .loadingImageId(R.mipmap.ic_launcher_round)
                .target(imageView)
                .build();
        ImageLoaderManager.getImageLoader().loadImage(config);
    }
}
