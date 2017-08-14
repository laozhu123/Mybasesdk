package xgn.com.my_basesdk.demo.activitys;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.LinkedList;

import javax.inject.Inject;

import xgn.com.basesdk.view.swipetoloadlayout.FixedSwipeToLoadLayout;
import xgn.com.my_basesdk.R;
import xgn.com.my_basesdk.base.activity.MyBaseBindPresentActivity;
import xgn.com.my_basesdk.demo.adapters.ListPageAdapter;
import xgn.com.my_basesdk.demo.beans.SimpleBean;
import xgn.com.my_basesdk.demo.interfaces.IUIListPage;
import xgn.com.my_basesdk.demo.presenters.PresenterListPage;
import xgn.com.my_basesdk.injecter.component.ActivityComponent;

public class ActivityListPage extends MyBaseBindPresentActivity<PresenterListPage> implements IUIListPage, ListPageAdapter.EmptyListener {

    private FixedSwipeToLoadLayout mRefreshLayout;
    private ListPageAdapter mAdapter;
    private LinkedList<SimpleBean> mList;
    private RecyclerView mRecyclerView;

    @Inject
    PresenterListPage presenterListPage;

    @Override
    protected int getContentLayoutResId() {
        return R.layout.activity_list_page;
    }

    @Override
    protected void initActivity(View pView) {
        mRefreshLayout = getRefreshLayout();
        setTitle("列表页");
        setBackIconVisiable(true);
        mList = new LinkedList<>();
        mAdapter = new ListPageAdapter(mList);
        mAdapter.setEmptyListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRefreshLayout.setRefreshing(true);
    }


    @Override
    public boolean useSwipeRefreshLayout() {
        return true;
    }


    @Override
    public void showList(int size) {
        if (mRefreshLayout.isRefreshing())
            mRefreshLayout.setRefreshing(false);
        if (mRefreshLayout.isLoadingMore())
            mRefreshLayout.setLoadingMore(false);
        if (size == -1)
            showErrorView();
        else {
            mList.clear();
            SimpleBean simpleBean;
            for (int i = 0; i < size; i++) {
                simpleBean = new SimpleBean();
                simpleBean.data = "" + (i + 1);
                mList.add(simpleBean);
            }
            showContent();
            mAdapter.updateList();
        }
    }

    @Override
    public PresenterListPage getPresenter() {
        return presenterListPage;
    }

    @Override
    protected void inject(ActivityComponent pActivityComponent) {
        pActivityComponent.inject(this);
    }

    @Override
    public void onRefresh() {
        presenterListPage.getHelo();
    }

    @Override
    public void onLoadMore() {
        presenterListPage.getHelo();
    }

    @Override
    public void reLoadData() {
        presenterListPage.getHelo();
    }

    @Override
    public void empty() {
        showEmptyView();
    }
}
