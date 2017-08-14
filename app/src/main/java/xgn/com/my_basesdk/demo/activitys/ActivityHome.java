package xgn.com.my_basesdk.demo.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import xgn.com.basesdk.base.mvp.BasePresenter;
import xgn.com.my_basesdk.R;
import xgn.com.my_basesdk.base.activity.MyBaseBindPresentActivity;
import xgn.com.my_basesdk.injecter.component.ActivityComponent;

public class ActivityHome extends MyBaseBindPresentActivity implements View.OnClickListener {
    TextView listPage;

    @Override
    protected int getContentLayoutResId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initActivity(View var1) {
        listPage = (TextView) findViewById(R.id.list_page);
        listPage.setOnClickListener(this);
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void inject(ActivityComponent pActivityComponent) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, ActivityListPage.class));
    }
}
