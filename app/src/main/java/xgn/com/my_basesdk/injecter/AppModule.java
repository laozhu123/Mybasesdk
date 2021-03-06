package xgn.com.my_basesdk.injecter;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import xgn.com.basesdk.base.injector.ContextLife;
import xgn.com.my_basesdk.app.MyApplication;

/**
 * Created by huluzi on 2017/8/10.
 */
@Module
public class AppModule {
    protected final MyApplication mApplication;

    public AppModule(MyApplication application) {
        mApplication = application;
    }

    @Provides
    @XGHostAppScope
    @ContextLife("Application")
    public Context provideContext() {
        return mApplication.getApplicationContext();
    }
}
