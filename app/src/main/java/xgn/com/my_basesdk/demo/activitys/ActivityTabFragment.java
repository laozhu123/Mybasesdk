package xgn.com.my_basesdk.demo.activitys;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import xgn.com.basesdk.base.mvp.BasePresenter;
import xgn.com.basesdk.commonui.utils.XgFragmentManager;
import xgn.com.my_basesdk.R;
import xgn.com.my_basesdk.base.activity.MyBaseBindPresentActivity;
import xgn.com.my_basesdk.demo.fragments.FragmentFirst;
import xgn.com.my_basesdk.demo.fragments.FragmentSecond;
import xgn.com.my_basesdk.injecter.component.ActivityComponent;
import xgn.com.my_basesdk.views.BottomBar;
import xgn.com.my_basesdk.views.BottomBarTab;

public class ActivityTabFragment extends MyBaseBindPresentActivity<BasePresenter> {


    @Bind(R.id.bottomBar)
    BottomBar mBottomBar;
    private XgFragmentManager mXgFragmentManager;
    private String mFragmentFirst;
    private String mFragmentSecond;

    @Override
    protected int getContentLayoutResId() {
        return R.layout.activity_tab_fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void initActivity(View view) {
        ButterKnife.bind(this, view);
        initFragments();
        initView();
        mXgFragmentManager.switchFragmentWithCache(mFragmentFirst, null);
    }

    private void initFragments() {
        mXgFragmentManager = new XgFragmentManager(this);
        mFragmentFirst = FragmentFirst.class.getName();
        mFragmentSecond = FragmentSecond.class.getName();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void initView() {

        mBottomBar.addItem(new BottomBarTab(this,
                R.drawable.icon_hall_mission_unselect,
                R.drawable.icon_hall_mission_selected,
                R.string.first_page))
                .addItem(new BottomBarTab(this,
                        R.drawable.icon_hall_mine_unselect,
                        R.drawable.icon_hall_mine_selected,
                        R.string.second_page));

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                if (position == 0) {
                    mXgFragmentManager.switchFragmentWithCache(mFragmentFirst, null);
                } else {
                    mXgFragmentManager.switchFragmentWithCache(mFragmentSecond, null);
                }
                // 如果不是位于 Tab 中的根页面时，TabBar 处于隐藏状态，这时候如果点击了通知打开 app 时会跳转到 app 的首页，
                // 这时候就需要将 Tab 显示出来
                if (position == 0) {
                    mBottomBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(int position) {
            }

            @Override
            public void onTabReselected(int position) {
            }
        });
    }

    @Override
    protected void inject(ActivityComponent pActivityComponent) {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
