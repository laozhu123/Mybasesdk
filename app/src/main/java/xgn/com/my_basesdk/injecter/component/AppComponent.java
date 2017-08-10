package xgn.com.my_basesdk.injecter.component;

import android.content.Context;

import dagger.Component;
import xgn.com.basesdk.base.component.CoreAppComponent;
import xgn.com.basesdk.base.injector.ContextLife;
import xgn.com.my_basesdk.app.MyApplication;
import xgn.com.my_basesdk.injecter.AppModule;
import xgn.com.my_basesdk.injecter.XGHostAppScope;
import xgn.com.my_basesdk.injecter.module.MyApiModule;
import xgn.com.my_basesdk.net.RetrofitApi;

/**
 * Created by Administrator on 2017/8/9.
 */

@XGHostAppScope
@Component(dependencies = {CoreAppComponent.class}, modules = {MyApiModule.class, AppModule.class})
public interface AppComponent extends CoreAppComponent {

    @ContextLife("Application")
    Context getAppContext();

    RetrofitApi getRetrofitApi();

    void inject(MyApplication myApplication);
}
