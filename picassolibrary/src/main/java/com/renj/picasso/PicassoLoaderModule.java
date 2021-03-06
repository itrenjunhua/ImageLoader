package com.renj.picasso;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.renj.imageloaderlibrary.config.ImageLoadConfig;
import com.renj.imageloaderlibrary.config.ImageModuleConfig;
import com.renj.imageloaderlibrary.loader.IImageLoaderModule;
import com.renj.picasso.manager.RequestCreatorRetriever;
import com.renj.picasso.transform.CircleTransformation;
import com.renj.picasso.transform.RoundTransformation;
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
    private ImageModuleConfig imageModuleConfig;

    @Override
    public void init(@NonNull ImageModuleConfig imageModuleConfig) {
        this.imageModuleConfig = imageModuleConfig;
    }

    @Override
    public void loadImage(@NonNull String url, @NonNull ImageView imageView) {
        ImageLoadConfig imageLoadConfig = new ImageLoadConfig.Builder()
                .url(url)
                .target(imageView)
                .build();
        loadImage(imageLoadConfig);
    }

    @Override
    public <T extends ImageLoadConfig> void loadImage(@NonNull T imageLoadConfig) {
        Picasso picasso = createPicasso(imageLoadConfig);
        RequestCreator requestCreator = loadPath(picasso, imageLoadConfig);
        // 绑定生命周期
        bandLifecycle(requestCreator, imageLoadConfig);
        // 读取配置信息，加载图片
        builderControl(requestCreator, imageLoadConfig);
    }

    private void builderControl(RequestCreator requestCreator, @NonNull ImageLoadConfig imageLoadConfig) {
        requestCreator = initImageInfoConfig(requestCreator, imageLoadConfig);
        // 加载图片
        intoOf(requestCreator, imageLoadConfig);
    }

    /**
     * 绑定生命周期
     */
    private void bandLifecycle(RequestCreator requestCreator, ImageLoadConfig imageLoadConfig) {
        if (imageLoadConfig.getFragmentV4() != null) {
            RequestCreatorRetriever.newInstance().addRequestCreate(imageLoadConfig.getFragmentV4(), requestCreator);
        } else if (imageLoadConfig.getFragment() != null) {
            RequestCreatorRetriever.newInstance().addRequestCreate(imageLoadConfig.getFragment(), requestCreator);
        } else if (imageLoadConfig.getFragmentActivity() != null) {
            RequestCreatorRetriever.newInstance().addRequestCreate(imageLoadConfig.getFragmentActivity(), requestCreator);
        } else if (imageLoadConfig.getActivity() != null) {
            RequestCreatorRetriever.newInstance().addRequestCreate(imageLoadConfig.getActivity(), requestCreator);
        } else if (imageLoadConfig.getTarget() != null) {
            RequestCreatorRetriever.newInstance().addRequestCreate(imageLoadConfig.getTarget(), requestCreator);
        } else if (imageLoadConfig.getContext() != null) {
            RequestCreatorRetriever.newInstance().addRequestCreate(imageLoadConfig.getContext(), requestCreator);
        } else if (imageModuleConfig.getApplication() != null) {
            RequestCreatorRetriever.newInstance().addRequestCreate(imageModuleConfig.getApplication(), requestCreator);
        }
    }

    /**
     * 加载图片到指定控件
     */
    private void intoOf(RequestCreator requestCreator, @NonNull ImageLoadConfig imageLoadConfig) {
        if (imageLoadConfig.getTarget() instanceof ImageView) {
            requestCreator.into((ImageView) imageLoadConfig.getTarget());
        }
    }

    /**
     * 将配置信息增加到Picasso中
     */
    private RequestCreator initImageInfoConfig(RequestCreator requestCreator, @NonNull ImageLoadConfig imageLoadConfig) {
        if (imageLoadConfig.getWidth() > 0 && imageLoadConfig.getHeight() > 0)
            requestCreator = requestCreator.resize(imageLoadConfig.getWidth(), imageLoadConfig.getHeight());
        if (imageLoadConfig.getWidth() > 0 && imageLoadConfig.getHeight() <= 0)
            requestCreator = requestCreator.resize(imageLoadConfig.getWidth(), imageLoadConfig.getWidth());
        if (imageLoadConfig.getHeight() > 0 && imageLoadConfig.getWidth() <= 0)
            requestCreator = requestCreator.resize(imageLoadConfig.getHeight(), imageLoadConfig.getHeight());

        if (imageLoadConfig.isSkipMemory())
            requestCreator = requestCreator.memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE);

        if (imageLoadConfig.isSkipDisk())
            requestCreator = requestCreator.networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE);

        if (imageModuleConfig.getLoadingRes() > 0)
            requestCreator = requestCreator.error(imageModuleConfig.getLoadingRes());
        if (imageModuleConfig.getErrorRes() > 0)
            requestCreator = requestCreator.placeholder(imageModuleConfig.getErrorRes());

        if (imageLoadConfig.getErrorImageId() > 0)
            requestCreator = requestCreator.error(imageLoadConfig.getErrorImageId());
        if (imageLoadConfig.getLoadingImageId() > 0)
            requestCreator = requestCreator.placeholder(imageLoadConfig.getLoadingImageId());

        if (imageLoadConfig.getErrorDrawable() != null)
            requestCreator = requestCreator.error(imageLoadConfig.getErrorDrawable());
        if (imageLoadConfig.getLoadingDrawable() != null)
            requestCreator = requestCreator.placeholder(imageLoadConfig.getLoadingDrawable());

        if (imageLoadConfig.isCenterCrop())
            requestCreator = requestCreator.centerCrop();
        if (imageLoadConfig.isFitCenter())
            requestCreator = requestCreator.fit();
        if (imageLoadConfig.isCenterInside())
            requestCreator = requestCreator.centerInside();
        if (imageLoadConfig.getRotateConfig() != null)
            requestCreator = requestCreator.rotate(imageLoadConfig.getRotateConfig().rotateRotationAngle, imageLoadConfig.getRotateConfig().pivotX, imageLoadConfig.getRotateConfig().pivotY);
        if (imageLoadConfig.getRoundConfig() != null)
            requestCreator = requestCreator.transform(new RoundTransformation(imageLoadConfig.getRoundConfig().radiusX, imageLoadConfig.getRoundConfig().radiusY));
        if (imageLoadConfig.isCircle())
            requestCreator = requestCreator.transform(new CircleTransformation());


        return requestCreator;
    }

    /**
     * 确定图片加载路径
     */
    @NonNull
    private RequestCreator loadPath(Picasso picasso, @NonNull ImageLoadConfig imageLoadConfig) {
        if (imageLoadConfig.getFilePath() != null)
            return picasso.load(new File(imageLoadConfig.getFilePath()));

        if (imageLoadConfig.getFile() != null)
            return picasso.load(imageLoadConfig.getFile());

        if (imageLoadConfig.getDrawableId() > 0)
            return picasso.load(imageLoadConfig.getDrawableId());

        if (imageLoadConfig.getUri() != null)
            return picasso.load(imageLoadConfig.getUri());

        // imageLoadConfig.getUrl() 也可能为 null
        return picasso.load(imageLoadConfig.getUrl());
    }

    /**
     * 调用 {@link Picasso#get()} 方法创建 {@link Picasso} 对象
     *
     * @param imageLoadConfig {@link ImageLoadConfig} 对象
     * @return {@link Picasso} 对象
     */
    private Picasso createPicasso(ImageLoadConfig imageLoadConfig) {

//        if (imageLoadConfig.getFragmentV4() != null)
//            return Picasso.with(imageLoadConfig.getFragmentV4().getActivity());
//
//        if (imageLoadConfig.getFragment() != null)
//            return Picasso.with(imageLoadConfig.getFragment().getActivity());
//
//        if (imageLoadConfig.getFragmentActivity() != null)
//            return Picasso.with(imageLoadConfig.getFragmentActivity());
//
//        if (imageLoadConfig.getActivity() != null)
//            return Picasso.with(imageLoadConfig.getActivity());
//
//        if (imageLoadConfig.getTarget() != null)
//            return Picasso.with(imageLoadConfig.getTarget().getContext());
//
//        if (imageLoadConfig.getContext() != null)
//            return Picasso.with(imageLoadConfig.getContext());
//
//        if (application != null)
//            return Picasso.with(application);

        return Picasso.get();
    }

    @Override
    public void pause() {
        RequestCreatorRetriever.newInstance().pause();
    }

    @Override
    public void resume() {
        RequestCreatorRetriever.newInstance().resume();
    }

    @Override
    public void clearMemoryCache() {

    }

    @Override
    public void clearDiskCache() {

    }
}
