package com.renj.imageloader.base;

import android.app.Fragment;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2018-03-15   10:22
 * <p>
 * 描述：RecyclerView.Adapter简单封装
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public abstract class CustomRecyclerAdapter<T> extends RecyclerView.Adapter<CustomViewHolder> {
    protected Context context;
    protected List<T> mDataList = new ArrayList<>();

    @LayoutRes
    private int layoutID;
    private OnItemClickListener<T> mOnItemClickListener;
    private OnItemLongClickListener<T> mOnItemLongClickListener;

    public CustomRecyclerAdapter(@NonNull Fragment fragment) {
        this(fragment.getActivity());
    }

    public CustomRecyclerAdapter(@NonNull Fragment fragment, List<T> dataList) {
        this(fragment.getActivity(), dataList);
    }

    public CustomRecyclerAdapter(@NonNull Fragment fragment, @LayoutRes int layoutID) {
        this(fragment.getActivity(), layoutID);
    }

    public CustomRecyclerAdapter(@NonNull Fragment fragment, List<T> dataList, @LayoutRes int layoutID) {
        this(fragment.getActivity(), dataList, layoutID);
    }

    public CustomRecyclerAdapter(@NonNull androidx.fragment.app.Fragment fragment) {
        this(fragment.getActivity());
    }

    public CustomRecyclerAdapter(@NonNull androidx.fragment.app.Fragment fragment, List<T> dataList) {
        this(fragment.getActivity(), dataList);
    }

    public CustomRecyclerAdapter(@NonNull androidx.fragment.app.Fragment fragment, @LayoutRes int layoutID) {
        this(fragment.getActivity(), layoutID);
    }

    public CustomRecyclerAdapter(@NonNull androidx.fragment.app.Fragment fragment, List<T> dataList, @LayoutRes int layoutID) {
        this(fragment.getActivity(), dataList, layoutID);
    }

    public CustomRecyclerAdapter(@NonNull Context context) {
        this.context = context;
    }

    public CustomRecyclerAdapter(@NonNull Context context, List<T> dataList) {
        this(context);
        this.mDataList = dataList;
        notifyDataSetChanged();
    }

    public CustomRecyclerAdapter(@NonNull Context context, @LayoutRes int layoutID) {
        this(context);
        this.layoutID = layoutID;
    }

    public CustomRecyclerAdapter(@NonNull Context context, List<T> dataList, @LayoutRes int layoutID) {
        this(context, dataList);
        this.layoutID = layoutID;
    }

    /**
     * 设置单击监听
     *
     * @param onItemClickListener {@link CustomRecyclerAdapter.OnItemClickListener} 对象
     */
    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /**
     * 设置长按监听
     *
     * @param onItemLongClickListener {@link CustomRecyclerAdapter.OnItemLongClickListener} 对象
     */
    public void setOnItemLongClickListener(OnItemLongClickListener<T> onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    /**
     * 重新设置 List 集合数据
     *
     * @param dataList
     */
    public void resetDatas(List<T> dataList) {
        if (dataList != null) {
            this.mDataList.clear();
            this.mDataList.addAll(dataList);
            notifyDataSetChanged();
        }
    }

    /**
     * 在 List 集合指定位置新增数据
     *
     * @param dataList
     */
    public void addDatas(int index, List<T> dataList) {
        if (dataList != null) {
            this.mDataList.addAll(index, dataList);
            notifyDataSetChanged();
        }
    }

    /**
     * 在 List 集合最后追加数据
     *
     * @param dataList
     */
    public void addDatas(List<T> dataList) {
        if (dataList != null) {
            this.mDataList.addAll(dataList);
            notifyDataSetChanged();
        }
    }

    /**
     * 获取 Adapter 中的数据
     *
     * @return
     */
    public List<T> getDatas() {
        return this.mDataList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CustomViewHolder(context, parent, layoutID);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final int temp = position;
        final T itemData = mDataList.get(position);
        setData(holder, itemData, position);

        holder.setOnItemViewClickListener(new CustomViewHolder.OnItemViewClickListener() {
            @Override
            public void onItemViewClick(View itemView) {
                if (null != mOnItemClickListener)
                    mOnItemClickListener.onItemClick(itemView, temp, mDataList, itemData);
            }
        });

        holder.setOnItemLongViewClickListener(new CustomViewHolder.OnItemLongViewClickListener() {
            @Override
            public boolean onItemLongViewClick(View itemView) {
                if (null != mOnItemLongClickListener)
                    return mOnItemLongClickListener.onItemLongClick(itemView, temp, mDataList, itemData);
                return false;
            }
        });
    }

    /**
     * 设置 itemView 中的数据
     *
     * @param holder   当前 item 的 ViewHolder 对象
     * @param itemData 当前 item 的数据
     * @param position
     */
    public abstract void setData(CustomViewHolder holder, T itemData, int position);

    /**
     * item 单击监听
     */
    public interface OnItemClickListener<T> {
        /**
         * @param itemView 单击的item View
         * @param position 单击的位置
         * @param dataList 所有Adapter中的数据
         * @param itemData 单击位置的数据
         */
        void onItemClick(View itemView, int position, List<T> dataList, T itemData);
    }


    /**
     * item 长按监听
     */
    public interface OnItemLongClickListener<T> {
        /**
         * @param itemView 长按的item View
         * @param position 长按的位置
         * @param dataList 所有Adapter中的数据
         * @param itemData 长按位置的数据
         * @return true表示处理长按事件，false表示不处理长按事件
         */
        boolean onItemLongClick(View itemView, int position, List<T> dataList, T itemData);
    }
}
