package xgn.com.my_basesdk.demo.activity;

import android.view.View;

import com.xgn.common.swipe_pull_load.swipetoloadlayout.SwipeToLoadLayout;

import javax.inject.Inject;

import xgn.com.my_basesdk.R;
import xgn.com.my_basesdk.base.activity.MyBaseBindPresentActivity;
import xgn.com.my_basesdk.demo.interfaces.IUIMain;
import xgn.com.my_basesdk.demo.presenter.PresenterMain;
import xgn.com.my_basesdk.injecter.component.ActivityComponent;

public class MainActivity extends MyBaseBindPresentActivity<PresenterMain> implements IUIMain {
    SwipeToLoadLayout refreshLayout;

    @Inject
    PresenterMain presenterMain;

    @Override
    protected int getContentLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initActivity(View pView) {
        refreshLayout = getRefreshLayout();
    }


    @Override
    public boolean useSwipeRefreshLayout() {
        return true;
    }

    @Override
    public void sayHelo() {
        showToast("helo");
    }

    @Override
    public PresenterMain getPresenter() {
        return presenterMain;
    }

    @Override
    protected void inject(ActivityComponent pActivityComponent) {
        pActivityComponent.inject(this);
    }

    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(false);
        presenterMain.getHelo();
    }

    @Override
    public void onLoadMore() {
        refreshLayout.setLoadingMore(false);
        presenterMain.getHelo();
    }
}
