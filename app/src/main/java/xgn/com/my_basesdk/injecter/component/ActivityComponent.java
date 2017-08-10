package xgn.com.my_basesdk.injecter.component;

/**
 * Created by huluzi on 2017/8/10.
 */

import android.content.Context;

import dagger.Component;
import xgn.com.basesdk.base.injector.ContextLife;
import xgn.com.my_basesdk.demo.activity.MainActivity;
import xgn.com.my_basesdk.injecter.ActivityScope;
import xgn.com.my_basesdk.injecter.module.ActivityModule;

@ActivityScope
@Component(
        dependencies = {AppComponent.class},
        modules = {ActivityModule.class})
public interface ActivityComponent extends AppComponent {

    @ContextLife("Activity")
    Context getContext();

    void inject(MainActivity mainActivity);
}
