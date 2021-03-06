package com.renj.picasso.manager;

import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.lang.ref.SoftReference;
import java.util.HashSet;
import java.util.Set;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * 邮箱：renjunhua@anlovek.com
 * <p>
 * 创建时间：2019-01-23   10:56
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class RequestCreatorFragment extends Fragment implements IRequestCreateManager {
    private Set<RequestCreator> requestCreatorSet = new HashSet<>();
    private final SoftReference<Set<RequestCreator>> requestCreators = new SoftReference<>(requestCreatorSet);

    @Override
    public void addRequestCreate(RequestCreator requestCreator) {
        if (requestCreator != null) {
            requestCreator.tag(this);
            requestCreators.get().add(requestCreator);
        }
    }

    @Override
    public void removeRequestCreate(RequestCreator requestCreator) {
        if (requestCreator != null)
            requestCreators.get().remove(requestCreator);
    }

    @Override
    @Nullable
    public Set<RequestCreator> getRequestCreatorSet() {
        return requestCreators.get();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("RequestCreatorFragment", "onStart -----  Picasso.get().resumeTag(this)");
        Picasso.get().resumeTag(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("RequestCreatorFragment", "onStop ======= Picasso.get().pauseTag(this)");
        Picasso.get().pauseTag(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("RequestCreatorFragment", "onDestroy >>>>>>>>>>> Picasso.get().cancelTag(this)");
        Picasso.get().cancelTag(this);
    }
}
