package xgn.com.my_basesdk.base.fragment;

import xgn.com.basesdk.base.fragment.BaseBindPresenterFragment;
import xgn.com.basesdk.base.mvp.BasePresenter;
import xgn.com.my_basesdk.base.ActivityComponentable;
import xgn.com.my_basesdk.injecter.component.ActivityComponent;
import xgn.com.my_basesdk.injecter.component.DaggerFragmentComponent;
import xgn.com.my_basesdk.injecter.component.FragmentComponent;
import xgn.com.my_basesdk.injecter.module.FragmentModule;

/**
 * Created by huluzi on 2017/8/14.
 */

public abstract class MyBindPresentFragment<P extends BasePresenter> extends BaseBindPresenterFragment<P> implements FragmentComponentable {

    protected FragmentComponent mFragmentComponent;

    @Override
    protected void setComponent() {
        if (getActivity() instanceof ActivityComponentable) {
            inject(((ActivityComponentable) getActivity()).getActivityComponent());
        }
    }


    private void inject(ActivityComponent pActivityComponent) {
        mFragmentComponent = DaggerFragmentComponent.builder()
                .activityComponent(pActivityComponent)
                .fragmentModule(new FragmentModule(this))
                .build();

        inject(mFragmentComponent);
    }


    protected abstract void inject(FragmentComponent pFragmentComponent);

    @Override
    public FragmentComponent getFragmentComponent() {
        return mFragmentComponent;
    }

    @Override
    public P getPresenter() {
        return null;
    }
}
