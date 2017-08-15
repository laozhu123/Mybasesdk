package xgn.com.my_basesdk.demo.activitys;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import xgn.com.basesdk.base.mvp.BasePresenter;
import xgn.com.my_basesdk.R;
import xgn.com.my_basesdk.base.activity.MyBaseBindPresentActivity;
import xgn.com.my_basesdk.demo.adapters.MyFragmentPagerAdapter;
import xgn.com.my_basesdk.demo.fragments.FragmentSwipeFirst;
import xgn.com.my_basesdk.demo.fragments.FragmentSwipeSecond;
import xgn.com.my_basesdk.demo.fragments.FragmentSwipeThird;
import xgn.com.my_basesdk.injecter.component.ActivityComponent;
import xgn.com.my_basesdk.views.BottomBar;
import xgn.com.my_basesdk.views.BottomBarTab;

public class ActivitySwipeFragment extends MyBaseBindPresentActivity {

    @Bind(R.id.vp)
    ViewPager viewpager;
    @Bind(R.id.bottomBar)
    BottomBar mBottomBar;
    @Bind(R.id.toolbar_swipe)
    Toolbar mToolbar1;

    private View mTitleBarBack;
    private TextView mTitleBarTitle;

    List<Fragment> listfragment;


    @Override
    protected int getContentLayoutResId() {
        return R.layout.activity_swipe_fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void initActivity(View view) {
        ButterKnife.bind(this, view);
        initToolbar();
        initView();
        addListener();
    }

    private void initToolbar() {
        View titleBar = this.getLayoutInflater().inflate(xgn.com.basesdk.R.layout.view_simple_title_bar, (ViewGroup) null);
        mTitleBarBack = titleBar.findViewById(xgn.com.basesdk.R.id.titlebar_back);
        mTitleBarTitle = (TextView) titleBar.findViewById(xgn.com.basesdk.R.id.titlebar_title);
        mTitleBarBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ActivitySwipeFragment.this.onBackPressed();
            }
        });
        Toolbar.LayoutParams lp = new Toolbar.LayoutParams(-1, -1);
        mToolbar1.addView(titleBar, lp);
        mTitleBarTitle.setText("swipe-fragment");
    }

    private void addListener() {
        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                viewpager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(int position) {
            }

            @Override
            public void onTabReselected(int position) {
            }
        });

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int index) {
                mBottomBar.setCurrentItem(index);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void initView() {
        listfragment = new ArrayList<>();
        Fragment f1 = new FragmentSwipeFirst();
        Fragment f2 = new FragmentSwipeSecond();
        Fragment f3 = new FragmentSwipeThird();
        listfragment.add(f1);
        listfragment.add(f2);
        listfragment.add(f3);

        FragmentManager fm = getSupportFragmentManager();
        MyFragmentPagerAdapter mfpa = new MyFragmentPagerAdapter(fm, listfragment);

        viewpager.setAdapter(mfpa);
        viewpager.setCurrentItem(0);

        mBottomBar.addItem(new BottomBarTab(this,
                R.drawable.icon_hall_mission_unselect,
                R.drawable.icon_hall_mission_selected,
                R.string.first_page))
                .addItem(new BottomBarTab(this,
                        R.drawable.icon_hall_mine_unselect,
                        R.drawable.icon_hall_mine_selected,
                        R.string.second_page))
                .addItem(new BottomBarTab(this,
                        R.drawable.icon_hall_mission_unselect,
                        R.drawable.icon_hall_mission_selected,
                        R.string.third_page));
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void inject(ActivityComponent pActivityComponent) {

    }
}
