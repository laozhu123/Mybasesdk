package xgn.com.my_basesdk.demo.fragments;

import android.view.View;

import xgn.com.basesdk.base.mvp.BasePresenter;
import xgn.com.my_basesdk.R;
import xgn.com.my_basesdk.base.fragment.MyBindPresentFragment;
import xgn.com.my_basesdk.injecter.component.FragmentComponent;

/**
 * Created by huluzi on 2017/8/14.
 */

public class FragmentFirst extends MyBindPresentFragment<BasePresenter> implements View.OnClickListener{
    @Override
    protected void inject(FragmentComponent pFragmentComponent) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_first_page;
    }

    @Override
    protected void initFragment(View view) {
        setTitle("tab-1");
        setLeftImage(R.drawable.icon_left_arrow);
        setLeftImageClick(this);
    }

    @Override
    public void onClick(View v) {
        getActivity().finish();
    }
}
