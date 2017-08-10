package xgn.com.my_basesdk.base.activity;

import xgn.com.basesdk.base.activity.BaseBindPresenterActivity;
import xgn.com.basesdk.base.mvp.BasePresenter;
import xgn.com.my_basesdk.app.MyApplication;
import xgn.com.my_basesdk.base.ActivityComponentable;
import xgn.com.my_basesdk.injecter.component.ActivityComponent;
import xgn.com.my_basesdk.injecter.component.DaggerActivityComponent;
import xgn.com.my_basesdk.injecter.module.ActivityModule;

/**
 * Created by huluzi on 2017/8/10.
 */

public abstract class MyBaseBindPresentActivity<P extends BasePresenter> extends BaseBindPresenterActivity<P> implements ActivityComponentable {
    protected ActivityComponent mActivityComponent;

    protected abstract void inject(ActivityComponent pActivityComponent);

    @Override
    protected void setComponent() {
        mActivityComponent = DaggerActivityComponent.builder()
                .appComponent(MyApplication.getMyAppInstance().getAppComponent())
                .activityModule(new ActivityModule(this))
                .build();
        inject(mActivityComponent);
    }

    @Override
    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }


}
