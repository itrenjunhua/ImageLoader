package com.renj.picasso.manager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.ArrayMap;
import android.view.View;

import com.renj.imageloaderlibrary.utils.CheckUtils;
import com.renj.imageloaderlibrary.utils.Utils;
import com.squareup.picasso.RequestCreator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * 邮箱：renjunhua@anlovek.com
 * <p>
 * 创建时间：2019-01-23   11:00
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class RequestCreatorRetriever {
    private static final RequestCreatorRetriever instance = new RequestCreatorRetriever();

    @VisibleForTesting
    static final String FRAGMENT_TAG = "com.renj.picasso.manager";

    @VisibleForTesting
    final Map<android.app.FragmentManager, RequestCreatorFragment> pendingRequestCreatorFragments = new HashMap<>();

    @VisibleForTesting
    final Map<FragmentManager, SupportRequestCreatorFragment> pendingSupportRequestCreatorFragments = new HashMap<>();

    private static final String FRAGMENT_INDEX_KEY = "key";
    private final ArrayMap<View, Fragment> tempViewToSupportFragment = new ArrayMap<>();
    private final ArrayMap<View, android.app.Fragment> tempViewToFragment = new ArrayMap<>();
    private final Bundle tempBundle = new Bundle();

    private RequestCreatorRetriever() {
    }

    public static RequestCreatorRetriever newInstance() {
        return instance;
    }

    public void addRequestCreate(Fragment fragment, RequestCreator requestCreator) {
        addRequestCreate(get(fragment), requestCreator);
    }

    public void addRequestCreate(android.app.Fragment fragment, RequestCreator requestCreator) {
        addRequestCreate(get(fragment), requestCreator);
    }

    public void addRequestCreate(FragmentActivity fragmentActivity, RequestCreator requestCreator) {
        addRequestCreate(get(fragmentActivity), requestCreator);
    }

    public void addRequestCreate(Activity activity, RequestCreator requestCreator) {
        addRequestCreate(get(activity), requestCreator);
    }

    public void addRequestCreate(View view, RequestCreator requestCreator) {
        addRequestCreate(get(view), requestCreator);
    }

    public void addRequestCreate(Context context, RequestCreator requestCreator) {
        addRequestCreate(get(context), requestCreator);
    }

    private void addRequestCreate(IRequestCreateManager iRequestCreateManager, RequestCreator requestCreator) {
        if (iRequestCreateManager != null) {
            iRequestCreateManager.addRequestCreate(requestCreator);
        }
    }

    public void removeRequestCreate(Fragment fragment, RequestCreator requestCreator) {
        removeRequestCreate(get(fragment), requestCreator);
    }

    public void removeRequestCreate(android.app.Fragment fragment, RequestCreator requestCreator) {
        removeRequestCreate(get(fragment), requestCreator);
    }

    public void removeRequestCreate(FragmentActivity fragmentActivity, RequestCreator requestCreator) {
        removeRequestCreate(get(fragmentActivity), requestCreator);
    }

    public void removeRequestCreate(Activity activity, RequestCreator requestCreator) {
        removeRequestCreate(get(activity), requestCreator);
    }

    public void removeRequestCreate(View view, RequestCreator requestCreator) {
        removeRequestCreate(get(view), requestCreator);
    }

    public void removeRequestCreate(Context context, RequestCreator requestCreator) {
        removeRequestCreate(get(context), requestCreator);
    }

    private void removeRequestCreate(IRequestCreateManager iRequestCreateManager, RequestCreator requestCreator) {
        if (iRequestCreateManager != null) {
            iRequestCreateManager.removeRequestCreate(requestCreator);
        }
    }

    public void removeRequestCreate(Fragment fragment) {
        getRequestCreatorSet(get(fragment));
    }

    public void removeRequestCreate(android.app.Fragment fragment) {
        getRequestCreatorSet(get(fragment));
    }

    public void removeRequestCreate(FragmentActivity fragmentActivity) {
        getRequestCreatorSet(get(fragmentActivity));
    }

    public void removeRequestCreate(Activity activity) {
        getRequestCreatorSet(get(activity));
    }

    public void removeRequestCreate(View view) {
        getRequestCreatorSet(get(view));
    }

    public void removeRequestCreate(Context context) {
        getRequestCreatorSet(get(context));
    }

    @Nullable
    private Set<RequestCreator> getRequestCreatorSet(IRequestCreateManager iRequestCreateManager) {
        return iRequestCreateManager.getRequestCreatorSet();
    }

    @Nullable
    private IRequestCreateManager get(@NonNull Context context) {
        if (context == null) {
            return null;
        } else if (Utils.isOnMainThread() && !(context instanceof Application)) {
            if (context instanceof FragmentActivity) {
                return get((FragmentActivity) context);
            } else if (context instanceof Activity) {
                return get((Activity) context);
            } else if (context instanceof ContextWrapper) {
                return get(((ContextWrapper) context).getBaseContext());
            }
        }
        return null;
    }

    @Nullable
    private IRequestCreateManager get(@NonNull FragmentActivity activity) {
        if (Utils.isOnBackgroundThread()) {
            return get(activity.getApplicationContext());
        } else {
            assertNotDestroyed(activity);
            FragmentManager fm = activity.getSupportFragmentManager();
            return getSupportRequestCreatorFragment(fm, isActivityVisible(activity));
        }
    }

    @Nullable
    private IRequestCreateManager get(@NonNull Fragment fragment) {
        CheckUtils.checkNotNull(fragment.getActivity(), "You cannot start a load on a fragment before it is attached or after it is destroyed");
        if (Utils.isOnBackgroundThread()) {
            return get(fragment.getActivity().getApplicationContext());
        } else {
            FragmentManager fm = fragment.getChildFragmentManager();
            return getSupportRequestCreatorFragment(fm, fragment.isVisible());
        }
    }

    @Nullable
    private IRequestCreateManager get(@NonNull Activity activity) {
        if (Utils.isOnBackgroundThread()) {
            return get(activity.getApplicationContext());
        } else {
            assertNotDestroyed(activity);
            android.app.FragmentManager fm = activity.getFragmentManager();
            return getRequestCreatorFragment(fm, isActivityVisible(activity));
        }
    }

    @Nullable
    private IRequestCreateManager get(@NonNull View view) {
        if (Utils.isOnBackgroundThread()) {
            return get(view.getContext().getApplicationContext());
        }

        CheckUtils.checkNotNull(view);
        CheckUtils.checkNotNull(view.getContext(), "Unable to obtain a request manager for a view without a Context");
        Activity activity = findActivity(view.getContext());
        if (activity == null) {
            return get(view.getContext().getApplicationContext());
        }

        if (activity instanceof FragmentActivity) {
            Fragment fragment = findSupportFragment(view, (FragmentActivity) activity);
            return fragment != null ? get(fragment) : get(activity);
        }

        android.app.Fragment fragment = findFragment(view, activity);
        if (fragment == null) {
            return get(activity);
        }
        return get(fragment);
    }

    private static void findAllSupportFragmentsWithViews(@Nullable Collection<Fragment> topLevelFragments, @NonNull Map<View, Fragment> result) {
        if (topLevelFragments == null) {
            return;
        }
        for (Fragment fragment : topLevelFragments) {
            if (fragment == null || fragment.getView() == null) {
                continue;
            }
            result.put(fragment.getView(), fragment);
            findAllSupportFragmentsWithViews(fragment.getChildFragmentManager().getFragments(), result);
        }
    }

    @Nullable
    private Fragment findSupportFragment(@NonNull View target, @NonNull FragmentActivity activity) {
        tempViewToSupportFragment.clear();
        findAllSupportFragmentsWithViews(activity.getSupportFragmentManager().getFragments(), tempViewToSupportFragment);
        Fragment result = null;
        View activityRoot = activity.findViewById(android.R.id.content);
        View current = target;
        while (!current.equals(activityRoot)) {
            result = tempViewToSupportFragment.get(current);
            if (result != null) {
                break;
            }
            if (current.getParent() instanceof View) {
                current = (View) current.getParent();
            } else {
                break;
            }
        }

        tempViewToSupportFragment.clear();
        return result;
    }

    @Nullable
    private android.app.Fragment findFragment(@NonNull View target, @NonNull Activity activity) {
        tempViewToFragment.clear();
        findAllFragmentsWithViews(activity.getFragmentManager(), tempViewToFragment);

        android.app.Fragment result = null;

        View activityRoot = activity.findViewById(android.R.id.content);
        View current = target;
        while (!current.equals(activityRoot)) {
            result = tempViewToFragment.get(current);
            if (result != null) {
                break;
            }
            if (current.getParent() instanceof View) {
                current = (View) current.getParent();
            } else {
                break;
            }
        }
        tempViewToFragment.clear();
        return result;
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void findAllFragmentsWithViews(@NonNull android.app.FragmentManager fragmentManager, @NonNull ArrayMap<View, android.app.Fragment> result) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            for (android.app.Fragment fragment : fragmentManager.getFragments()) {
                if (fragment.getView() != null) {
                    result.put(fragment.getView(), fragment);
                    findAllFragmentsWithViews(fragment.getChildFragmentManager(), result);
                }
            }
        } else {
            findAllFragmentsWithViewsPreO(fragmentManager, result);
        }
    }

    private void findAllFragmentsWithViewsPreO(@NonNull android.app.FragmentManager fragmentManager, @NonNull ArrayMap<View, android.app.Fragment> result) {
        int index = 0;
        while (true) {
            tempBundle.putInt(FRAGMENT_INDEX_KEY, index++);
            android.app.Fragment fragment = null;
            try {
                fragment = fragmentManager.getFragment(tempBundle, FRAGMENT_INDEX_KEY);
            } catch (Exception e) {

            }
            if (fragment == null) {
                break;
            }
            if (fragment.getView() != null) {
                result.put(fragment.getView(), fragment);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    findAllFragmentsWithViews(fragment.getChildFragmentManager(), result);
                }
            }
        }
    }

    @Nullable
    private Activity findActivity(@NonNull Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        } else if (context instanceof ContextWrapper) {
            return findActivity(((ContextWrapper) context).getBaseContext());
        } else {
            return null;
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static void assertNotDestroyed(@NonNull Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.isDestroyed()) {
            throw new IllegalArgumentException("You cannot start a load for a destroyed activity");
        }
    }

    @NonNull
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private IRequestCreateManager get(@NonNull android.app.Fragment fragment) {
        if (fragment.getActivity() == null) {
            throw new IllegalArgumentException("You cannot start a load on a fragment before it is attached");
        }
        if (Utils.isOnBackgroundThread() || Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return get(fragment.getActivity().getApplicationContext());
        } else {
            android.app.FragmentManager fm = fragment.getChildFragmentManager();
            return getRequestCreatorFragment(fm, fragment.isVisible());
        }
    }

    @NonNull
    RequestCreatorFragment getRequestCreatorFragment(Activity activity) {
        return getRequestCreatorFragment(activity.getFragmentManager(), isActivityVisible(activity));
    }

    @NonNull
    RequestCreatorFragment getRequestCreatorFragment(@NonNull final android.app.FragmentManager fm, boolean isParentVisible) {
        RequestCreatorFragment current = (RequestCreatorFragment) fm.findFragmentByTag(FRAGMENT_TAG);
        if (current == null) {
            current = pendingRequestCreatorFragments.get(fm);
            if (current == null) {
                current = new RequestCreatorFragment();
                if (isParentVisible) {
                    current.onStart();
                }
                pendingRequestCreatorFragments.put(fm, current);
                fm.beginTransaction().add(current, FRAGMENT_TAG).commitAllowingStateLoss();
            }
        }
        return current;
    }

    private static boolean isActivityVisible(Activity activity) {
        return !activity.isFinishing();
    }

    @NonNull
    SupportRequestCreatorFragment getSupportRequestCreatorFragment(FragmentActivity activity) {
        return getSupportRequestCreatorFragment(activity.getSupportFragmentManager(), isActivityVisible(activity));
    }

    @NonNull
    SupportRequestCreatorFragment getSupportRequestCreatorFragment(@NonNull final FragmentManager fm, boolean isParentVisible) {
        SupportRequestCreatorFragment current = (SupportRequestCreatorFragment) fm.findFragmentByTag(FRAGMENT_TAG);
        if (current == null) {
            current = pendingSupportRequestCreatorFragments.get(fm);
            if (current == null) {
                current = new SupportRequestCreatorFragment();
                if (isParentVisible) {
                    current.onStart();
                }
                pendingSupportRequestCreatorFragments.put(fm, current);
                fm.beginTransaction().add(current, FRAGMENT_TAG).commitAllowingStateLoss();
            }
        }
        return current;
    }
}
