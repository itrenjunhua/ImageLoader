package com.renj.imageloaderlibrary.picasso;

import com.renj.imageloaderlibrary.loader.ImageInfoConfig;

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

    protected PicassoImageInfoConfig(Builder builder) {
        super(builder);
    }

    public Object getTag() {
        return tag;
    }


    public static class Builder extends ImageInfoConfig.Builder {
        private Object tag; // 标记

        /**
         * 设置Tag
         *
         * @param tag Tag 对象
         */
        public Builder tag(Object tag) {
            this.tag = tag;
            return this;
        }

        public PicassoImageInfoConfig build() {
            return new PicassoImageInfoConfig(this);
        }
    }
}
