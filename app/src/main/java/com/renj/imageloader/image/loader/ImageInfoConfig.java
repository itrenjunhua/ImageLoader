package com.renj.imageloader.image.loader;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import java.io.File;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2018-03-08   10:32
 * <p>
 * 描述：加载图片信息配置
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class ImageInfoConfig {
    private View target; // 图片展示目标控件
    private Context context;
    /*** 当选择Glide框架时，with() 方法可以传递context之外，还可以传递一下参数 ***/
    private Activity activity;
    private FragmentActivity fragmentActivity;
    private Fragment fragment;
    private android.support.v4.app.Fragment fragmentV4;

    /***************图片路径信息********************/
    private String url; // 网络图片路径
    private String filePath; // 文件路径
    private File file; // 文件路径
    @DrawableRes
    private int drawableId;  // 资源id
    private Uri uri; // 图片 uri
    private byte[] bytes; // 图片字节数组
    private Bitmap bitmap; // Bitmap对象
    private Drawable drawable; // Drawable 对象

    private boolean isGif; // 是否 Gif 图片
    private boolean isBitmap; // 是否作为 Bitmap 显示

    @IntRange(from = 0)
    private int width; // 图片宽
    @IntRange(from = 0)
    private int height; // 图片高
    @FloatRange(from = 0)
    private float thumbnail; // 缩略图缩放倍数

    private ImageInfoConfig(Builder builder) {
        this.target = builder.target;
        this.context = builder.context;
        this.activity = builder.activity;
        this.fragmentActivity = builder.fragmentActivity;
        this.fragment = builder.fragment;
        this.fragmentV4 = builder.fragmentV4;
        this.url = builder.url;
        this.filePath = builder.filePath;
        this.file = builder.file;
        this.drawableId = builder.drawableId;
        this.uri = builder.uri;
        this.bytes = builder.bytes;
        this.bitmap = builder.bitmap;
        this.drawable = builder.drawable;
        this.isGif = builder.isGif;
        this.isBitmap = builder.isBitmap;
        this.width = builder.width;
        this.height = builder.height;
        this.thumbnail = builder.thumbnail;
    }

    public View getTarget() {
        return target;
    }

    public Context getContext() {
        return context;
    }

    public Activity getActivity() {
        return activity;
    }

    public FragmentActivity getFragmentActivity() {
        return fragmentActivity;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public android.support.v4.app.Fragment getFragmentV4() {
        return fragmentV4;
    }

    public String getUrl() {
        return url;
    }

    public String getFilePath() {
        return filePath;
    }

    public File getFile() {
        return file;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public Uri getUri() {
        return uri;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public boolean isGif() {
        return isGif;
    }

    public boolean isBitmap() {
        return isBitmap;
    }

    public int getWidth() {
        if (width <= 0) {
            //先去imageview里取,如果为0,则赋值成matchparent
            if (target != null) {
                width = target.getMeasuredWidth();
            }
            if (width <= 0) {
                width = ImageLoaderUitls.getWinWidth();
            }
        }
        return width;
    }

    public int getHeight() {
        if (height <= 0) {
            //先去imageview里取,如果为0,则赋值成matchparent
            if (target != null) {
                height = target.getMeasuredWidth();
            }
            if (height <= 0) {
                height = ImageLoaderUitls.getWinHeight();
            }
        }
        return height;
    }

    public float getThumbnail() {
        return thumbnail;
    }

    public static class Builder {
        private View target; // 图片展示目标控件
        private Context context;
        /*** 当选择Glide框架时，with() 方法可以传递context之外，还可以传递一下参数 ***/
        private Activity activity;
        private FragmentActivity fragmentActivity;
        private Fragment fragment;
        private android.support.v4.app.Fragment fragmentV4;

        /***************图片路径信息********************/
        private String url; // 网络图片路径
        private String filePath; // 文件路径
        private File file; // 文件路径
        @DrawableRes
        private int drawableId;  // 资源id
        private Uri uri; // 图片 uri
        private byte[] bytes; // 图片字节数组
        private Bitmap bitmap; // Bitmap对象
        private Drawable drawable; // Drawable 对象

        private boolean isGif; // 是否 Gif 图片
        private boolean isBitmap; // 是否作为 Bitmap 显示

        @IntRange(from = 0)
        private int width; // 图片宽
        @IntRange(from = 0)
        private int height; // 图片高
        @FloatRange(from = 0)
        private float thumbnail; // 缩略图缩放倍数

        public Builder() {
        }

        public Builder target(@NonNull View target) {
            this.target = target;
            return this;
        }

        public Builder context(@NonNull Context context) {
            this.context = context;
            return this;
        }

        public Builder activity(@NonNull Activity activity) {
            this.activity = activity;
            return this;
        }

        public Builder fragmentActivity(@NonNull FragmentActivity fragmentActivity) {
            this.fragmentActivity = fragmentActivity;
            return this;
        }

        public Builder fragment(@NonNull Fragment fragment) {
            this.fragment = fragment;
            return this;
        }

        public Builder fragmentV4(@NonNull android.support.v4.app.Fragment fragmentV4) {
            this.fragmentV4 = fragmentV4;
            return this;
        }

        public Builder url(@NonNull String url) {
            this.url = url;
            return this;
        }

        public Builder filePath(@NonNull String filePath) {
            this.filePath = filePath;
            return this;
        }

        public Builder file(@NonNull File file) {
            this.file = file;
            return this;
        }

        public Builder drawableId(@DrawableRes int drawableId) {
            this.drawableId = drawableId;
            return this;
        }

        public Builder uri(@NonNull Uri uri) {
            this.uri = uri;
            return this;
        }

        public Builder bytes(@NonNull byte[] bytes) {
            this.bytes = bytes;
            return this;
        }

        public Builder bitmap(@NonNull Bitmap bitmap) {
            this.bitmap = bitmap;
            return this;
        }

        public Builder drawable(@NonNull Drawable drawable) {
            this.drawable = drawable;
            return this;
        }

        public Builder asGif() {
            this.isGif = true;
            return this;
        }

        public Builder asBitmap() {
            this.isBitmap = true;
            return this;
        }

        public Builder width(@IntRange(from = 0) int width) {
            this.width = width;
            return this;
        }

        public Builder height(@IntRange(from = 0) int height) {
            this.height = height;
            return this;
        }

        public Builder thumbnail(@FloatRange(from = 0) float thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public ImageInfoConfig build() {
            return new ImageInfoConfig(this);
        }
    }
}
