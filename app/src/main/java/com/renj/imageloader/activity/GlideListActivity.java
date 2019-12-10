package com.renj.imageloader.activity;

import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.renj.imageloader.R;
import com.renj.imageloader.base.BaseActivity;
import com.renj.imageloader.base.CustomViewHolder;
import com.renj.imageloader.base.CustomRecyclerAdapter;
import com.renj.imageloader.utils.ImageLoaderManager;
import com.renj.imageloader.utils.ImageUrl;

import java.util.List;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * 邮箱：renjunhua@anlovek.com
 * <p>
 * 创建时间：2019-03-25   15:26
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class GlideListActivity extends BaseActivity {
    RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_list;
    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.recycler_view);
        List<String> images = ImageUrl.getData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(new CustomRecyclerAdapter<String>(this, images, R.layout.activity_list_item) {
            @Override
            public void setData(CustomViewHolder holder, String itemData, int position) {
                ImageView imageView = holder.getView(R.id.image_view);
                ImageLoaderManager.loadImage(GlideListActivity.this, itemData, imageView);
            }
        });
    }

    @Override
    protected void initData() {

    }
}
