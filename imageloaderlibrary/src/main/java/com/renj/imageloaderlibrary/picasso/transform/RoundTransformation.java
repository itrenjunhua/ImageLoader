package com.renj.imageloaderlibrary.picasso.transform;

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
 * 创建时间：2019-01-16   16:21
 * <p>
 * 描述：Picasso 中用于将 {@link Bitmap} 变为圆角图片的 {@link Transformation}
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class RoundTransformation implements Transformation {
    private int radiusX, radiusY;
    private Random random;

    /**
     * 构造
     *
     * @see #RoundTransformation(int)
     * @see #RoundTransformation(int, int)
     */
    public RoundTransformation() {
        this(4, 4);
    }

    /**
     * 构造，指定圆角大小，默认 4
     *
     * @param radius 圆角大小
     * @see #RoundTransformation()
     * @see #RoundTransformation(int, int)
     */
    public RoundTransformation(int radius) {
        this(radius, radius);
    }

    /**
     * 构造，指定x轴方向和y轴方向的圆角大小，默认 4
     *
     * @param radiusX x方向圆角大小
     * @param radiusY y方向圆角大小
     * @see #RoundTransformation()
     * @see #RoundTransformation(int)
     */
    public RoundTransformation(int radiusX, int radiusY) {
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        random = new Random();
    }

    @Override
    public Bitmap transform(Bitmap source) {
        if (source == null) return null;
        return BitmapUtils.makeRoundRect(source, radiusX, radiusY);
    }

    @Override
    public String key() {
        // 返回转换的唯一键，用于缓存目的
        return "round_transform - " + System.currentTimeMillis() + " - " + random.nextLong();
    }
}
