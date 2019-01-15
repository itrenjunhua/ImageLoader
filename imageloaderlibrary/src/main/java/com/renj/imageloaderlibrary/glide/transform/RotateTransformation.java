package com.renj.imageloaderlibrary.glide.transform;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * 邮箱：renjunhua@anlovek.com
 * <p>
 * 创建时间：2019-01-15   17:09
 * <p>
 * 描述：旋转 Transformation
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class RotateTransformation extends BitmapTransformation {
    private float rotateRotationAngle = 0f;
    private float pivotX = 0f, pivotY = 0f;

    public RotateTransformation(float rotateRotationAngle) {
        this.rotateRotationAngle = rotateRotationAngle;
    }

    public RotateTransformation(float rotateRotationAngle, float pivotX, float pivotY) {
        this.rotateRotationAngle = rotateRotationAngle;
        this.pivotX = pivotX;
        this.pivotY = pivotY;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        Matrix matrix = new Matrix();

        matrix.postRotate(rotateRotationAngle, pivotX, pivotY);

        return Bitmap.createBitmap(toTransform, 0, 0, toTransform.getWidth(), toTransform.getHeight(), matrix, true);
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }
}
