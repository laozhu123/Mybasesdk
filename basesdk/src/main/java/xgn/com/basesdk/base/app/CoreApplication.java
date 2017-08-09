package xgn.com.basesdk.base.app;

import android.app.Application;
import android.support.annotation.NonNull;

import xgn.com.basesdk.base.component.CoreAppComponent;
import xgn.com.basesdk.base.component.DaggerCoreAppComponent;
import xgn.com.basesdk.network.XgNetWork;
import xgn.com.basesdk.network.interfaces.INetExternalParams;

/**
 * Created by huluzi on 2017/7/13.
 */

public class CoreApplication extends Application {

    private static CoreApplication sInstance;
    protected CoreAppComponent mCoreComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        initNetWork();
        initCoreGraph();
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

    public void initCoreGraph() {
        mCoreComponent = DaggerCoreAppComponent.builder()
                .build();
    }

    public static Application getInstance() {
        return sInstance;
    }

    public CoreAppComponent getmCoreComponent() {
        return mCoreComponent;
    }

    public void setmCoreComponent(CoreAppComponent mCoreComponent) {
        this.mCoreComponent = mCoreComponent;
    }
}