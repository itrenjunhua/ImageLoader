package com.renj.imageloader.image;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
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
    /**
     * 显示图片目标View
     */
    private View target;
    /**
     * 上下文
     */
    private Context context;
    /***当选择Glide框架时，with() 方法可以传递context之外，还可以传递一下参数***/
    private Activity activity;
    private FragmentActivity fragmentActivity;
    private Fragment fragment;
    private android.support.v4.app.Fragment fragmentV4;

    /***************图片路径信息********************/
    private String url; // 网络图片路径
    private String filePath; //文件路径
    private File file; //文件路径
    @DrawableRes
    private int drawableId;  //资源id
    private Uri uri;
    private byte[] bytes;
    private Bitmap bitmap;
    private Drawable drawable;
    private boolean isGif;
    private boolean isBitmap;

    public ImageInfoConfig(Builder builder) {
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

    public static class Builder {
        private View target;
        private Context context;
        /*** 当选择Glide框架时，with() 方法可以传递context之外，还可以传递一下参数 ***/
        private Activity activity;
        private FragmentActivity fragmentActivity;
        private Fragment fragment;
        private android.support.v4.app.Fragment fragmentV4;

        /***************图片路径信息********************/
        private String url; // 网络图片路径
        private String filePath; //文件路径
        private File file; //文件路径
        @DrawableRes
        private int drawableId;  //资源id
        private Uri uri;
        private byte[] bytes;
        private Bitmap bitmap;
        private Drawable drawable;
        private boolean isGif;
        private boolean isBitmap;

        public Builder() {
        }

        public Builder target(View target) {
            this.target = target;
            return this;
        }

        public Builder context(Context context) {
            this.context = context;
            return this;
        }

        public Builder activity(Activity activity) {
            this.activity = activity;
            return this;
        }

        public Builder fragmentActivity(FragmentActivity fragmentActivity) {
            this.fragmentActivity = fragmentActivity;
            return this;
        }

        public Builder fragment(Fragment fragment) {
            this.fragment = fragment;
            return this;
        }

        public Builder fragmentV4(android.support.v4.app.Fragment fragmentV4) {
            this.fragmentV4 = fragmentV4;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder filePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public Builder file(File file) {
            this.file = file;
            return this;
        }

        public Builder drawableId(@DrawableRes int drawableId) {
            this.drawableId = drawableId;
            return this;
        }

        public Builder uri(Uri uri) {
            this.uri = uri;
            return this;
        }

        public Builder bytes(byte[] bytes) {
            this.bytes = bytes;
            return this;
        }

        public Builder bitmap(Bitmap drawable) {
            this.bitmap = bitmap;
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

        public ImageInfoConfig build() {
            return new ImageInfoConfig(this);
        }
    }
}
