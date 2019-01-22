package com.ren.picasso.manager;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * 邮箱：renjunhua@anlovek.com
 * <p>
 * 创建时间：2019-01-22   16:21
 * <p>
 * 描述：生命周期监听，与{@link android.app.Fragment} or {@link android.app.Activity} 的生命周期绑定
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public interface LifecycleListener {
    /**
     * Callback for when {@link android.app.Fragment#onStart()}} or {@link
     * android.app.Activity#onStart()} is called.
     */
    void onStart();

    /**
     * Callback for when {@link android.app.Fragment#onStop()}} or {@link
     * android.app.Activity#onStop()}} is called.
     */
    void onStop();

    /**
     * Callback for when {@link android.app.Fragment#onDestroy()}} or {@link
     * android.app.Activity#onDestroy()} is called.
     */
    void onDestroy();
}
