package com.renj.imageloaderlibrary.picasso;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;

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
public class PicassoLoaderModule implements IPicassoLoaderModule {
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
        Picasso picasso = createPicasso(imageInfoConfig);
        RequestCreator requestCreator = loadPath(picasso, imageInfoConfig);
        builderControl(requestCreator, imageInfoConfig);
    }

    private void builderControl(RequestCreator requestCreator, @NonNull ImageInfoConfig imageInfoConfig) {
        requestCreator = initImageInfoConfig(requestCreator, imageInfoConfig);
        intoOf(requestCreator, imageInfoConfig);
    }

    /**
     * 加载图片到指定控件
     */
    private void intoOf(RequestCreator requestCreator, @NonNull ImageInfoConfig imageInfoConfig) {
        if (imageInfoConfig.getTarget() instanceof ImageView) {
            requestCreator.into((ImageView) imageInfoConfig.getTarget());
        }
    }

    /**
     * 将配置信息增加到Picasso中
     */
    private RequestCreator initImageInfoConfig(RequestCreator requestCreator, @NonNull ImageInfoConfig imageInfoConfig) {
        if (imageInfoConfig.getWidth() > 0 && imageInfoConfig.getHeight() > 0)
            requestCreator = requestCreator.resize(imageInfoConfig.getWidth(), imageInfoConfig.getHeight());
        if (imageInfoConfig.getWidth() > 0 && imageInfoConfig.getHeight() <= 0)
            requestCreator = requestCreator.resize(imageInfoConfig.getWidth(), imageInfoConfig.getWidth());
        if (imageInfoConfig.getHeight() > 0 && imageInfoConfig.getWidth() <= 0)
            requestCreator = requestCreator.resize(imageInfoConfig.getHeight(), imageInfoConfig.getHeight());

        if (imageInfoConfig.isSkipMemory())
            requestCreator = requestCreator.memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE);

        if (imageInfoConfig.isSkipDisk())
            requestCreator = requestCreator.networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE);

        if (imageInfoConfig.getErrorImageId() > 0)
            requestCreator = requestCreator.error(imageInfoConfig.getErrorImageId());
        if (imageInfoConfig.getLoadingImageId() > 0)
            requestCreator = requestCreator.placeholder(imageInfoConfig.getLoadingImageId());

        if (imageInfoConfig.getErrorDrawable() != null)
            requestCreator = requestCreator.error(imageInfoConfig.getErrorDrawable());
        if (imageInfoConfig.getLoadingDrawable() != null)
            requestCreator = requestCreator.placeholder(imageInfoConfig.getLoadingDrawable());

        if(imageInfoConfig instanceof PicassoImageInfoConfig) {
            PicassoImageInfoConfig picassoImageInfoConfig = (PicassoImageInfoConfig) imageInfoConfig;
            if (picassoImageInfoConfig.getTag() != null)
                requestCreator = requestCreator.tag(picassoImageInfoConfig.getTag());
        }

        return requestCreator;
    }

    /**
     * 确定图片加载路径
     */
    @NonNull
    private RequestCreator loadPath(Picasso picasso, @NonNull ImageInfoConfig imageInfoConfig) {
        if (imageInfoConfig.getFilePath() != null)
            return picasso.load(new File(imageInfoConfig.getFilePath()));

        if (imageInfoConfig.getFile() != null)
            return picasso.load(imageInfoConfig.getFile());

        if (imageInfoConfig.getDrawableId() > 0)
            return picasso.load(imageInfoConfig.getDrawableId());

        if (imageInfoConfig.getUri() != null)
            return picasso.load(imageInfoConfig.getUri());

        // imageInfoConfig.getUrl() 也可能为 null
        return picasso.load(imageInfoConfig.getUrl());
    }

    /**
     * 调用 {@link Picasso#with(Context)} 方法创建 {@link Picasso} 对象
     *
     * @param imageInfoConfig {@link ImageInfoConfig} 对象
     * @return {@link Picasso} 对象
     */
    private Picasso createPicasso(ImageInfoConfig imageInfoConfig) {
        if (imageInfoConfig.getFragmentV4() != null)
            return Picasso.with(imageInfoConfig.getFragmentV4().getActivity());

        if (imageInfoConfig.getFragment() != null)
            return Picasso.with(imageInfoConfig.getFragment().getActivity());

        if (imageInfoConfig.getFragmentActivity() != null)
            return Picasso.with(imageInfoConfig.getFragmentActivity());

        if (imageInfoConfig.getActivity() != null)
            return Picasso.with(imageInfoConfig.getActivity());

        if (imageInfoConfig.getContext() != null)
            return Picasso.with(imageInfoConfig.getContext());

        if (imageInfoConfig.getTarget() != null)
            return Picasso.with(imageInfoConfig.getTarget().getContext());

        if (application != null)
            return Picasso.with(application);

        throw new NullPointerException("Picasso 获取不到 Context");
    }

    @Override
    public void pauseTag(Object tag){
        Picasso.with(application).pauseTag(tag);
    }

    @Override
    public void resumeTag(Object tag){
        Picasso.with(application).resumeTag(tag);
    }
}
