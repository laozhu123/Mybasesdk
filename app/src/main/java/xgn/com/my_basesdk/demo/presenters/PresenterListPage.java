package xgn.com.my_basesdk.demo.presenters;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import xgn.com.basesdk.base.mvp.BasePresenter;
import xgn.com.basesdk.commonui.rxjava.XgSubscriber;
import xgn.com.basesdk.network.ExceptionHandle;
import xgn.com.my_basesdk.R;
import xgn.com.my_basesdk.demo.interfaces.IUIListPage;
import xgn.com.my_basesdk.net.RetrofitApi;
import xgn.com.my_basesdk.net.responses.Helo;

/**
 * Created by huluzi on 2017/8/10.
 */

public class PresenterListPage extends BasePresenter<IUIListPage> {

    private RetrofitApi mRetrofitApi;

    @Inject
    public PresenterListPage(RetrofitApi retrofitApi) {
        mRetrofitApi = retrofitApi;
    }

    public void getHelo() {
        mRetrofitApi.helo("laozhu", new Object())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new XgSubscriber<Helo>(this, true) {
                    @Override
                    public boolean onFailed(ExceptionHandle.ResponseThrowable responseThrowable) {
                        if (getMvpView() != null) {
                            getMvpView().showList(-1);
                        }
                        return false;
                    }

                    @Override
                    public void onNext(@NonNull Helo response) {
                        if (getMvpView() != null) {
                            getMvpView().showList(response.receiverInfoList.size());
                        }
                    }

                    @Override
                    public int getDialogMessage() {
                        return R.string.loading;
                    }
                });
    }

}
