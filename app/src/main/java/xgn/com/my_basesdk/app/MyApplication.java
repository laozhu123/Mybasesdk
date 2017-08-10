package xgn.com.my_basesdk.app;

import android.support.annotation.NonNull;

import xgn.com.basesdk.base.app.CoreApplication;
import xgn.com.basesdk.network.XgNetWork;
import xgn.com.basesdk.network.interfaces.INetExternalParams;
import xgn.com.my_basesdk.injecter.AppModule;
import xgn.com.my_basesdk.injecter.component.AppComponent;
import xgn.com.my_basesdk.injecter.component.DaggerAppComponent;

/**
 * Created by Administrator on 2017/8/9.
 */

public class MyApplication extends CoreApplication {

    private AppComponent mAppComponent;
    public static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        setupMyGraph();
        inject();
        initNetWork();

    }

    private void initNetWork() {
        XgNetWork.init(new XgNetWork.Builder(this).externalParams(new INetExternalParams() {
            @Override
            public String getUserId() {
                return null;
            }

            @Override
            public String getAppVersion() {
                return null;
            }

            @Override
            public boolean isRelease() {
                return false;
            }

            @NonNull
            @Override
            public String httpHost() {
                return "http://172.16.103.14";
            }

            @NonNull
            @Override
            public String httpSecondHost() {
                return null;
            }

            @NonNull
            @Override
            public String mockHost() {
                return null;
            }

            @Override
            public int connectTimeOut() {
                return 0;
            }
        }).build());
    }

    private void inject() {
        mAppComponent.inject(this);
    }

    private void setupMyGraph() {
        mAppComponent = DaggerAppComponent.builder()
                .coreAppComponent(mCoreComponent)
                .appModule(new AppModule(this)).build();
    }

    public static MyApplication getMyAppInstance() {
        return mInstance;
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}
