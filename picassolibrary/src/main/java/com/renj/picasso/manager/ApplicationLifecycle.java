package com.renj.picasso.manager;

import android.support.annotation.NonNull;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * 邮箱：renjunhua@anlovek.com
 * <p>
 * 创建时间：2019-01-23   10:25
 * <p>
 * 描述：和应用生命周期一样
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class ApplicationLifecycle implements Lifecycle {
    @Override
    public void addListener(@NonNull LifecycleListener listener) {
        listener.onStart();
    }

    @Override
    public void removeListener(@NonNull LifecycleListener listener) {

    }
}
