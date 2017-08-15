package xgn.com.my_basesdk.demo.activitys;

import android.content.Intent;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import xgn.com.basesdk.base.mvp.BasePresenter;
import xgn.com.my_basesdk.R;
import xgn.com.my_basesdk.base.activity.MyBaseBindPresentActivity;
import xgn.com.my_basesdk.injecter.component.ActivityComponent;

public class ActivityHome extends MyBaseBindPresentActivity<BasePresenter> {

    @Override
    protected int getContentLayoutResId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initActivity(View view) {
        ButterKnife.bind(this, view);
        setTitle("首页");
        setBackIconVisiable(false);
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void inject(ActivityComponent pActivityComponent) {

    }

    @OnClick({R.id.list_page, R.id.tab_fragment,R.id.swipe_fragment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.list_page:
                startActivity(new Intent(this, ActivityListPage.class));
                break;
            case R.id.tab_fragment:
                startActivity(new Intent(this, ActivityTabFragment.class));
                break;
            case R.id.swipe_fragment:
                startActivity(new Intent(this, ActivitySwipeFragment.class));
                break;
        }
    }


}
