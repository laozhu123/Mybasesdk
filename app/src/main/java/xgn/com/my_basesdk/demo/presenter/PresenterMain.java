package xgn.com.my_basesdk.demo.presenter;

import javax.inject.Inject;

import xgn.com.basesdk.base.mvp.BasePresenter;
import xgn.com.my_basesdk.demo.interfaces.IUIMain;
import xgn.com.my_basesdk.net.RetrofitApi;

/**
 * Created by huluzi on 2017/8/10.
 */

public class PresenterMain extends BasePresenter<IUIMain> {

    RetrofitApi mRetrofitApi;

    @Inject
    public PresenterMain(RetrofitApi retrofitApi) {
        mRetrofitApi = retrofitApi;
    }

    public void getHelo() {
        if (getMvpView() != null)
            getMvpView().sayHelo();
    }

}
