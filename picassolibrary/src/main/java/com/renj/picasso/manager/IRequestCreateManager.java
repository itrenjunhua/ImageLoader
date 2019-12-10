package com.renj.picasso.manager;

import androidx.annotation.Nullable;

import com.squareup.picasso.RequestCreator;

import java.util.Set;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * 邮箱：renjunhua@anlovek.com
 * <p>
 * 创建时间：2019-01-23   14:10
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public interface IRequestCreateManager {
    void addRequestCreate(RequestCreator requestCreator);

    void removeRequestCreate(RequestCreator requestCreator);

    @Nullable
    Set<RequestCreator> getRequestCreatorSet();
}
