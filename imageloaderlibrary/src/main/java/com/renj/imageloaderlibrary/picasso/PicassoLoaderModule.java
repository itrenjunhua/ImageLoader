package com.renj.imageloaderlibrary.picasso;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.renj.imageloaderlibrary.loader.IImageLoaderModule;
import com.renj.imageloaderlibrary.loader.ImageInfoConfig;
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
        ImageInfoConfig imageInfoConfig = new ImageInfoConfig.Builder()
                .context(context)
                .url(url)
                .target(imageView)
                .build();
        loadImage(imageInfoConfig);
    }

    @Override
    public void loadImage(@NonNull ImageInfoConfig imageInfoConfig) {
        Picasso picasso = createPicasso(imageInfoConfig);
        RequestCreator requestCreator = loadPath(picasso, imageInfoConfig);
        if (imageInfoConfig.getTarget() instanceof ImageView) {
            requestCreator.into((ImageView) imageInfoConfig.getTarget());
        }
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
}
