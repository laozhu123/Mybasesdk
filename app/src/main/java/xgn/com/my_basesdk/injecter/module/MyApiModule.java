package xgn.com.my_basesdk.injecter.module;


import dagger.Module;
import dagger.Provides;
import xgn.com.basesdk.network.XGRest;
import xgn.com.my_basesdk.injecter.XGHostAppScope;
import xgn.com.my_basesdk.net.RetrofitApi;

/**
 * Created by huluzi on 2017/8/10.
 */

@Module
public class MyApiModule {
    @Provides
    @XGHostAppScope
    RetrofitApi provideRetrofit(XGRest xgRest) {
        return xgRest.create(RetrofitApi.class);
    }
}
