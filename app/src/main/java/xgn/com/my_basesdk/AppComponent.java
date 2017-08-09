package xgn.com.my_basesdk;

import dagger.Component;
import xgn.com.basesdk.base.component.CoreAppComponent;

/**
 * Created by Administrator on 2017/8/9.
 */

@UserScope
@Component(dependencies = CoreAppComponent.class)
public interface AppComponent extends CoreAppComponent {

    void inject(MyApplication myApplication);
}
