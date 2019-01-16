package com.renj.imageloaderlibrary.picasso;

import android.support.annotation.NonNull;

import com.renj.imageloaderlibrary.loader.ImageInfoConfig;
import com.squareup.picasso.Transformation;

import java.util.List;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2018-05-27   18:06
 * <p>
 * 描述：Picasso 框架加载图片信息配置类
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class PicassoImageInfoConfig extends ImageInfoConfig {
    private Object tag; // 标记
    private Transformation transformation;
    private List<Transformation> transformations;

    protected PicassoImageInfoConfig(Builder builder) {
        super(builder);
        this.tag = builder.tag;
        this.transformation = builder.transformation;
        this.transformations = builder.transformations;
    }

    public Object getTag() {
        return tag;
    }

    public Transformation getTransformation() {
        return transformation;
    }

    public List<Transformation> getTransformations() {
        return transformations;
    }

    public static class Builder extends ImageInfoConfig.Builder {
        private Object tag; // 标记
        private Transformation transformation;
        private List<Transformation> transformations;

        /**
         * 设置Tag
         *
         * @param tag Tag 对象
         */
        public Builder tag(Object tag) {
            this.tag = tag;
            return this;
        }


        /**
         * 对图像进行处理
         *
         * @param transformation
         */
        public Builder transformation(@NonNull Transformation transformation) {
            this.transformation = transformation;
            return this;
        }

        /**
         * 对图像进行处理
         *
         * @param transformations
         */
        public Builder transformations(@NonNull List<Transformation> transformations) {
            this.transformations = transformations;
            return this;
        }

        public PicassoImageInfoConfig build() {
            return new PicassoImageInfoConfig(this);
        }
    }
}
