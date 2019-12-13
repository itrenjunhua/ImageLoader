# ImageLoader
图片加载框架二次封装，方便快速更换底层加载框架。

## 使用
1. 调用初始化方法
初始化方法，指定加载啊图片使用的框架，指定一个默认的；同时可以添加多个。

       ImageLoaderModule.initImageLoaderModule(
                        new ImageModuleConfig.Builder(application)
                                .defaultImageLoadModule(ImageLoadLibrary.GLIDE_LIBRARY, new GlideLoaderModule())
                                .addImageLoadModule(ImageLoadLibrary.PICASSO_LIBRARY, new PicassoLoaderModule())
                                .build());

2. 加载图片
使用 `ImageLoadConfig` 类配置加载图片的相关信息，然后调用方法加载图片

        ImageLoadConfig config = new ImageLoadConfig.Builder()
                        .asBitmap()
                        .thumbnail(0.2f)
                        .activity(activity)
                        .url(url)
                        .loadingImageId(R.mipmap.ic_launcher_round)
                        .target(imageView)
                        .build();
        ImageLoaderManager.getDefaultImageLoaderModule().loadImage(config);

如果不使用默认的加载方式，那么可以使用如下方法获取在初始化时添加的 `ImageLoadModule`

    ImageLoaderModule.getImageLoaderModule(ImageLoadLibrary.PICASSO_LIBRARY)

## 扩展
目前，该库已经实现了使用 Picasso 和 Glide 加载图片的方法，如果需要使用其他的图片，可以模拟Picasso或者Glide的实现进行自定义扩展。


## 混淆

* imageloaderlibrary库

		-keep class com.renj.imageloaderlibrary.**{*;}
		-dontwarn com.renj.imageloaderlibrary.**
    
* glidelibrary库

		-keep class com.renj.glide.**{*;}
		-dontwarn com.renj.glide.**
		
		# Glide库混淆
		-keep public class * implements com.bumptech.glide.module.GlideModule
        -keep public class * extends com.bumptech.glide.module.AppGlideModule
        -keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
          **[] $VALUES;
          public *;
        }
        # for DexGuard only
        -keepresourcexmlelements manifest/application/meta-data@value=GlideModule

* picassolibrary库

		-keep class com.renj.picasso.**{*;}
		-dontwarn com.renj.picasso.**
		
		# picasso库混淆
        -keep class com.squareup.picasso.** {*; }
        -dontwarn com.squareup.picasso.**
