package com.renj.imageloaderlibrary.picasso;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.renj.imageloaderlibrary.loader.IImageLoaderModule;
import com.renj.imageloaderlibrary.loader.ImageInfoConfig;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.File;

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
        ImageInfoConfig imageInfoConfig = new PicassoImageInfoConfig.Builder()
                .context(context)
                .url(url)
                .target(imageView)
                .build();
        loadImage(imageInfoConfig);
    }

    @Override
    public <T extends ImageInfoConfig> void loadImage(@NonNull T imageInfoConfig) {
        if (!(imageInfoConfig instanceof PicassoImageInfoConfig))
            throw new IllegalArgumentException(getClass().getName() + "#loadImage(ImageInfoConfig) 方法的参数为" + PicassoImageInfoConfig.class.getName());

        PicassoImageInfoConfig picassoImageInfoConfig = (PicassoImageInfoConfig) imageInfoConfig;
        Picasso picasso = createPicasso(picassoImageInfoConfig);
        RequestCreator requestCreator = loadPath(picasso, picassoImageInfoConfig);
        builderControl(requestCreator, picassoImageInfoConfig);
    }

    private void builderControl(RequestCreator requestCreator, @NonNull PicassoImageInfoConfig picassoImageInfoConfig) {
        requestCreator = initImageInfoConfig(requestCreator, picassoImageInfoConfig);
        intoOf(requestCreator, picassoImageInfoConfig);
    }

    /**
     * 加载图片到指定控件
     */
    private void intoOf(RequestCreator requestCreator, @NonNull PicassoImageInfoConfig picassoImageInfoConfig) {
        if (picassoImageInfoConfig.getTarget() instanceof ImageView) {
            requestCreator.into((ImageView) picassoImageInfoConfig.getTarget());
        }
    }

    /**
     * 将配置信息增加到Picasso中
     */
    private RequestCreator initImageInfoConfig(RequestCreator requestCreator, @NonNull PicassoImageInfoConfig picassoImageInfoConfig) {
        if (picassoImageInfoConfig.getWidth() > 0 && picassoImageInfoConfig.getHeight() > 0)
            requestCreator = requestCreator.resize(picassoImageInfoConfig.getWidth(), picassoImageInfoConfig.getHeight());
        if (picassoImageInfoConfig.getWidth() > 0 && picassoImageInfoConfig.getHeight() <= 0)
            requestCreator = requestCreator.resize(picassoImageInfoConfig.getWidth(), picassoImageInfoConfig.getWidth());
        if (picassoImageInfoConfig.getHeight() > 0 && picassoImageInfoConfig.getWidth() <= 0)
            requestCreator = requestCreator.resize(picassoImageInfoConfig.getHeight(), picassoImageInfoConfig.getHeight());

        if (picassoImageInfoConfig.isSkipMemory())
            requestCreator = requestCreator.memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE);

        if (picassoImageInfoConfig.isSkipDisk())
            requestCreator = requestCreator.networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE);

        if (picassoImageInfoConfig.getErrorImageId() > 0)
            requestCreator = requestCreator.error(picassoImageInfoConfig.getErrorImageId());
        if (picassoImageInfoConfig.getLoadingImageId() > 0)
            requestCreator = requestCreator.placeholder(picassoImageInfoConfig.getLoadingImageId());

        if (picassoImageInfoConfig.getTag() != null)
            requestCreator = requestCreator.tag(picassoImageInfoConfig.getTag());

        return requestCreator;
    }

    /**
     * 确定图片加载路径
     */
    @NonNull
    private RequestCreator loadPath(Picasso picasso, @NonNull PicassoImageInfoConfig picassoImageInfoConfig) {
        if (picassoImageInfoConfig.getFilePath() != null)
            return picasso.load(new File(picassoImageInfoConfig.getFilePath()));

        if (picassoImageInfoConfig.getFile() != null)
            return picasso.load(picassoImageInfoConfig.getFile());

        if (picassoImageInfoConfig.getDrawableId() > 0)
            return picasso.load(picassoImageInfoConfig.getDrawableId());

        if (picassoImageInfoConfig.getUri() != null)
            return picasso.load(picassoImageInfoConfig.getUri());

        // picassoImageInfoConfig.getUrl() 也可能为 null
        return picasso.load(picassoImageInfoConfig.getUrl());
    }

    /**
     * 调用 {@link Picasso#with(Context)} 方法创建 {@link Picasso} 对象
     *
     * @param picassoImageInfoConfig {@link ImageInfoConfig} 对象
     * @return {@link Picasso} 对象
     */
    private Picasso createPicasso(PicassoImageInfoConfig picassoImageInfoConfig) {
        if (picassoImageInfoConfig.getFragmentV4() != null)
            return Picasso.with(picassoImageInfoConfig.getFragmentV4().getActivity());

        if (picassoImageInfoConfig.getFragment() != null)
            return Picasso.with(picassoImageInfoConfig.getFragment().getActivity());

        if (picassoImageInfoConfig.getFragmentActivity() != null)
            return Picasso.with(picassoImageInfoConfig.getFragmentActivity());

        if (picassoImageInfoConfig.getActivity() != null)
            return Picasso.with(picassoImageInfoConfig.getActivity());

        if (picassoImageInfoConfig.getContext() != null)
            return Picasso.with(picassoImageInfoConfig.getContext());

        if (picassoImageInfoConfig.getTarget() != null)
            return Picasso.with(picassoImageInfoConfig.getTarget().getContext());

        if (application != null)
            return Picasso.with(application);

        throw new NullPointerException("Picasso 获取不到 Context");
    }
}
