package xgn.com.my_basesdk.app;

import android.support.annotation.NonNull;

import com.squareup.okhttp.Interceptor;

import java.util.ArrayList;
import java.util.List;

import xgn.com.basesdk.base.app.CoreApplication;
import xgn.com.basesdk.network.XgNetWork;
import xgn.com.basesdk.network.interfaces.INetExternalParams;
import xgn.com.my_basesdk.BuildConfig;
import xgn.com.my_basesdk.StethoIniter;
import xgn.com.my_basesdk.injecter.AppModule;
import xgn.com.my_basesdk.injecter.component.AppComponent;
import xgn.com.my_basesdk.injecter.component.DaggerAppComponent;
import xgn.com.my_basesdk.net.TokenInterceptor;

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
        List<Interceptor> intercepteors = new ArrayList<>();
        List<Interceptor> networkIntercepteors = new ArrayList<>();
        intercepteors.add(new TokenInterceptor());
        StethoIniter.init(this);
        if (StethoIniter.getInterptor() != null) {
            networkIntercepteors.add(StethoIniter.getInterptor());
        }
        XgNetWork build = new XgNetWork.Builder(this).externalParams(new INetExternalParams() {
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
                return BuildConfig.BUILD_TYPE.equals("debug");
            }

            @NonNull
            @Override
            public String httpHost() {
                return "http://172.16.1.180:8005/";  //主域名
            }

            @NonNull
            @Override
            public String httpSecondHost() {
                return "http://sjyxtest.yiqiguang.com/tbbuser/";  //第二个域名
            }

            @NonNull
            @Override
            public String mockHost() {
                return Servers.TBB_MOCK;  //mock域名
            }

            @Override
            public int connectTimeOut() {
                return 0;
            }
        })
                .extraHeaders(null)
                .interceptors(intercepteors)
                .networkInterceptors(networkIntercepteors)
                .build();
        XgNetWork.init(build);
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
