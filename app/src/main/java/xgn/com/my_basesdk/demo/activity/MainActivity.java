package xgn.com.my_basesdk.demo.activity;

import android.view.View;

import javax.inject.Inject;

import xgn.com.basesdk.view.swipetoloadlayout.swipetoloadlayout.SwipeToLoadLayout;
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
        setTitle("首页");
        setBackIconVisiable(true);

    }


    @Override
    public boolean useSwipeRefreshLayout() {
        return true;
    }

    @Override
    public void sayHelo(String helo) {
        if (refreshLayout.isRefreshing())
            refreshLayout.setRefreshing(false);
        if (refreshLayout.isLoadingMore())
            refreshLayout.setLoadingMore(false);
        showToast(helo);
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
        presenterMain.getHelo();
    }

    @Override
    public void onLoadMore() {
        presenterMain.getHelo();
    }
}
