package xgn.com.my_basesdk;

import android.view.View;
import android.widget.TextView;

import xgn.com.basesdk.base.activity.ActivityBase;

public class MainActivity extends ActivityBase {

    private TextView mTvHelo;

    @Override
    protected int getContentLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initActivity(View pView) {
        mTvHelo = (TextView) pView.findViewById(R.id.helo);
        setTitle("main");
        showOrHideToolbarSmooth(false);
    }

    @Override
    public boolean useSwipeRefreshLayout() {
        return true;
    }

    @Override
    public void reLoadData() {
        mTvHelo.postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity.this.setRefreshComplete();
            }
        },2000);
    }
}
