# ImageLoader
图片加载框架二次封装，定义基本类，可以简单加载图片；同时可以进行自定义进行扩展(但扩展的话，更换底层框架时(如Glide替换成Picasso)需要注意单独处理)

---
# 使用
实现接口方法，使用具体的图片加载框架实现接口方法加载图片(Demo中以实现和扩展Glide和Picasso的简单形式)

## 使用建议
建议在项目中建立一个图片加载管理类(ImageLoaderManager)，在这个类中调用二次封装的图片加载初始化方法和获取对应Module的方法  

    public class ImageLoaderManager {
        /**
         * 初始化自定义图片加载框架
         *
         * @param application {@link Application} 对象
         */
        public static void init(@NonNull Application application) {
            ImageLoaderModule.initImageLoaderModule(application, new GlideLoaderModule());
        }
    
        /**
         * 获取图片加载Module {@link com.renj.imageloaderlibrary.loader.IImageLoaderModule} 的子类对象。
         * <b>注意：这里返回的结果为{@link com.renj.imageloaderlibrary.loader.IImageLoaderModule} 子类对象，具体是哪一个根据在{@link #init(Application)} 方法中
         * 调用 {@link ImageLoaderModule#initImageLoaderModule(Application, IImageLoaderModule)} 方法传递的第二个参数确定。</b>
         *
         * @return 返回 {@link com.renj.imageloaderlibrary.loader.IImageLoaderModule} 子类对象
         */
        public static GlideLoaderModule getImageLoader() {
            return ImageLoaderModule.getImageLoaderModule();
        }
    }
    
## 基本方法使用/公共方法使用(使用ImageLoaderManager类)
1.在`Applicatio`n中初始化框架  

	ImageLoaderManager.init(this);
  
2.获取 `ImageLoaderModule`并加载图片  

    String url = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=4258600537,2998618099&fm=27&gp=0.jpg";

    // 使用最简单的形式加载图片
    ImageLoaderManager.getImageLoader().loadImage(this,url,imageView);
    // 使用ImageInfoConfig配置基本加载信息
    ImageInfoConfig config = new ImageInfoConfig.Builder()
            .context(this)
            .url(url)
            .loadingImageId(R.mipmap.ic_launcher_round)
            .target(imageView)
            .width(300)
            .height(200)
            .build();
    ImageLoaderManager.getImageLoader().loadImage(config);

## 不同加载框架(Glide、Picasso等)独有方法使用
简单举例Glide的 `thumbnail`方法

	String url = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=4258600537,2998618099&fm=27&gp=0.jpg";

    ImageInfoConfig config = new GlideImageInfoConfig.Builder()
            .asBitmap() // Glide
            .thumbnail(0.2f) // Glide
            .context(MainActivity.this)
            .url(url)
            //.drawableId(R.mipmap.ic_launcher)
            .loadingImageId(R.mipmap.ic_launcher_round)
            .target(imageView)
            .width(300)
            .height(200)
            .build();
    ImageLoaderManager.getImageLoader().loadImage(config);

---

# 基本类和接口
基本接口（`IImageLoaderModule`）：定义加载图片的公共方法  
基本类/配置类（`ImageInfoConfig`）：定义图片加载的基本属性  
使用具体的图片加载框架时，需要创建类实现 `IImageLoaderModule` 接口，并添加`ImageInfoConfig`的配置信息实现加载图片的具体方法

# 可自定义扩展类
如果不仅需要最基本的加载图片，还需要使用Glide或者Picasso的特殊方法，那么可以扩展`IImageLoaderModule` 接口和`ImageInfoConfig`配置类。