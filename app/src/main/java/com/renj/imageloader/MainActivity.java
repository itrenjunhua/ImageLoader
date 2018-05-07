package com.renj.imageloader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.renj.imageloaderlibrary.loader.ImageInfoConfig;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageview);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=4258600537,2998618099&fm=27&gp=0.jpg";
                ImageInfoConfig config = new ImageInfoConfig.Builder()
                        .context(MainActivity.this)
                        .url(url)
                        //.drawableId(R.mipmap.ic_launcher)
                        .loadingImageId(R.mipmap.ic_launcher_round)
                        .target(imageView)
                        .asBitmap()
                        .width(300)
                        .height(200)
                        .thumbnail(0.2f)
                        .build();
                ImageLoaderManager.getImageLoader().loadImage(config);
            }
        });
    }
}
