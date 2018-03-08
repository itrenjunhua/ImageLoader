package com.renj.imageloader.image;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.View;

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
    /**
     * 当选择Glide框架时，with() 方法可以传递context之外，还可以传递一下参数
     **/
    private Activity activity;
    private Fragment fragment;
    private android.support.v4.app.Fragment fragmentV4;

    public ImageInfoConfig(View target, Context context, Activity activity,
                           Fragment fragment, android.support.v4.app.Fragment fragmentV4) {
        this.target = target;
        this.context = context;
        this.activity = activity;
        this.fragment = fragment;
        this.fragmentV4 = fragmentV4;
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

    public Fragment getFragment() {
        return fragment;
    }

    public android.support.v4.app.Fragment getFragmentV4() {
        return fragmentV4;
    }

    public static class Builder {
        private View target;
        private Context context;
        /* 当选择Glide框架时，with() 方法可以传递context之外，还可以传递一下参数 */
        private Activity activity;
        private Fragment fragment;
        private android.support.v4.app.Fragment fragmentV4;

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

        public Builder fragment(Fragment fragment) {
            this.fragment = fragment;
            return this;
        }

        public Builder fragmentV4(android.support.v4.app.Fragment fragmentV4) {
            this.fragmentV4 = fragmentV4;
            return this;
        }

        public ImageInfoConfig build() {
            return new ImageInfoConfig(target, context, activity, fragment, fragmentV4);
        }
    }
}
