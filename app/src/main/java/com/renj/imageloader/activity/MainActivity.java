package com.renj.imageloader.activity;

import android.content.Intent;

import com.renj.imageloader.R;
import com.renj.imageloader.base.BaseActivity;

public class MainActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        findViewById(R.id.bt_glide).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GlideListActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.bt_picasso).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PicassoListActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void initData() {

    }

}
