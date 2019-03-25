package com.renj.imageloader.base;

import android.app.Fragment;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import java.util.List;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2018-03-15   10:36
 * <p>
 * 描述：有多种 item 类型时的 RecyclerView.Adapter
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public abstract class MultipleTypeAdapter<T> extends SingleTypeAdapter<T> {
    public MultipleTypeAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public MultipleTypeAdapter(@NonNull Fragment fragment, List<T> dataList) {
        super(fragment, dataList);
    }

    public MultipleTypeAdapter(@NonNull android.support.v4.app.Fragment fragment) {
        super(fragment);
    }

    public MultipleTypeAdapter(@NonNull android.support.v4.app.Fragment fragment, List<T> dataList) {
        super(fragment, dataList);
    }

    public MultipleTypeAdapter(@NonNull Context context) {
        super(context);
    }

    public MultipleTypeAdapter(@NonNull Context context, List<T> dataList) {
        super(context,dataList);
    }

    @Override
    public int getItemViewType(int position) {
        return itemViewType(position);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return itemViewHolder(context, parent, viewType);
    }

    /**
     * 返回当前 position 的 item 类型
     *
     * @param position
     * @return
     */
    public abstract int itemViewType(int position);

    /**
     * 返回当前 position item 的 ViewHolder， ViewHolder 为 {@link CustomViewHolder} 的子类对象
     *
     * @param context
     * @param parent
     * @param viewType 当前 item 的类型
     * @return
     */
    public abstract CustomViewHolder itemViewHolder(Context context, ViewGroup parent, int viewType);
}
