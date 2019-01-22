package com.ren.picasso.manager;

import android.support.annotation.NonNull;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * 邮箱：renjunhua@anlovek.com
 * <p>
 * 创建时间：2019-01-22   16:22
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public interface Lifecycle {
    /**
     * Adds the given listener to the set of listeners managed by this Lifecycle implementation.
     */
    void addListener(@NonNull LifecycleListener listener);

    /**
     * Removes the given listener from the set of listeners managed by this Lifecycle implementation,
     * returning {@code true} if the listener was removed successfully, and {@code false} otherwise.
     *
     * <p>This is an optimization only, there is no guarantee that every added listener will
     * eventually be removed.
     */
    void removeListener(@NonNull LifecycleListener listener);
}
