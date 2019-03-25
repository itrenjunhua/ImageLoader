package com.renj.imageloader.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.renj.imageloader.R;
import com.renj.imageloader.base.BaseActivity;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bt_glide)
    Button btGlide;
    @BindView(R.id.bt_picasso)
    Button btPicasso;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        btGlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GlideListActivity.class);
                startActivity(intent);
            }
        });

        btPicasso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PicassoListActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {

    }

}
