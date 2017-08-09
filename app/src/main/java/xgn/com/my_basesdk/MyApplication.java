package xgn.com.my_basesdk;

import javax.inject.Inject;

import xgn.com.basesdk.base.app.CoreApplication;
import xgn.com.basesdk.network.XGRest;

/**
 * Created by Administrator on 2017/8/9.
 */

public class MyApplication extends CoreApplication {

    protected AppComponent appComponent;
    public static MyApplication mInstance;

    @Inject
    XGRest xgRest;

    @Override
    public void onCreate() {
        super.onCreate();
        initMyGraph();
        inject();
        mInstance = this;
    }

    private void inject() {
        appComponent.inject(this);
    }

    private void initMyGraph() {
        appComponent = DaggerAppComponent.builder()
                .coreAppComponent(mCoreComponent).build();
    }

    public static MyApplication getMyAppInstance() {
        return mInstance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public XGRest getXgRest() {
        return xgRest;
    }
}
