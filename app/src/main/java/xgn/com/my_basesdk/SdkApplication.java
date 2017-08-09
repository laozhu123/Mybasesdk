package xgn.com.my_basesdk;

import android.content.Context;

import xgn.com.basesdk.base.app.CoreApplication;

/**
 * Created by huluzi on 2017/7/13.
 */

public class SdkApplication  extends CoreApplication {
//    @Inject
//    protected XGRest sXgRest;

//    private AppComponent mAppComponent;
    private static SdkApplication sdkApp;
    @Override
    public void onCreate() {
        super.onCreate();
        setupTbbGraph();
//        initNetWork();
        initRouter();
        initImageLoader();
//        mAppComponent.inject(this);
        sdkApp = this;

    }

    private void initImageLoader() {
//        ImageLoader.init(this);
    }


    private void initRouter() {
//        ARouter.init(this);
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }

    /** 获取App实例*/
    public static SdkApplication getAppInstance() {
        return sdkApp;
    }

    public void setupTbbGraph() {
//        mAppComponent = DaggerAppComponent.builder()
//                .coreAppComponent(mCoreComponent)
//                .applicationModule(new ApplicationModule(this))
//                .build();
    }

//    public AppComponent getComponent() {
//        return mAppComponent;
//    }


}
