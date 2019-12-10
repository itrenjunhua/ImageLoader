package com.renj.picasso.manager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
 * 创建时间：2019-01-23   10:50
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class SupportRequestCreatorFragment extends Fragment implements IRequestCreateManager {
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
        Picasso.get().resumeTag(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        Picasso.get().resumeTag(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Picasso.get().cancelTag(this);
    }
}
