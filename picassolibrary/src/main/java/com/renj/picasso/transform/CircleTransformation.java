package com.renj.picasso.transform;

import android.graphics.Bitmap;

import com.renj.imageloaderlibrary.utils.BitmapUtils;
import com.squareup.picasso.Transformation;

import java.util.Random;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * 邮箱：renjunhua@anlovek.com
 * <p>
 * 创建时间：2019-01-16   16:56
 * <p>
 * 描述：Picasso 中用于将 {@link Bitmap} 变为圆形图片的 {@link Transformation}
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class CircleTransformation implements Transformation {
    private Random random;

    public CircleTransformation() {
        this.random = new Random();
    }

    @Override
    public Bitmap transform(Bitmap source) {
        if (source == null) return null;
        Bitmap result = BitmapUtils.makeCircleImage(source);
        BitmapUtils.recyclerBitmap(source);
        return result;
    }

    @Override
    public String key() {
        // 返回转换的唯一键，用于缓存目的
        return "circle_transform - " + System.currentTimeMillis() + " - " + random.nextLong();
    }
}
