package com.renj.imageloader.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.renj.imageloader.R;
import com.renj.imageloader.base.BaseActivity;
import com.renj.imageloader.base.CustomViewHolder;
import com.renj.imageloader.base.CustomRecyclerAdapter;
import com.renj.imageloader.utils.ImageLoaderManager;
import com.renj.imageloader.utils.ImageUrl;

import java.util.List;

import butterknife.BindView;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * 邮箱：renjunhua@anlovek.com
 * <p>
 * 创建时间：2019-03-25   15:27
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class PicassoListActivity extends BaseActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_list;
    }

    @Override
    protected void initView() {
        List<String> images = ImageUrl.getData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(new CustomRecyclerAdapter<String>(this, images, R.layout.activity_list_item) {
            @Override
            public void setData(CustomViewHolder holder, String itemData, int position) {
                ImageView imageView = holder.getView(R.id.image_view);
                ImageLoaderManager.loadCircleImagePicasso(PicassoListActivity.this, itemData, imageView);
            }
        });
    }

    @Override
    protected void initData() {

    }
}
