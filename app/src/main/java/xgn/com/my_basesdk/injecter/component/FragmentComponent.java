package xgn.com.my_basesdk.injecter.component;

import android.support.v4.app.Fragment;

import dagger.Component;
import xgn.com.basesdk.base.injector.ContextLife;
import xgn.com.basesdk.commonui.injector.FragmentScope;
import xgn.com.my_basesdk.injecter.module.FragmentModule;


@FragmentScope
@Component(
		dependencies = {ActivityComponent.class},
		modules = {FragmentModule.class}
)
public interface FragmentComponent {

	@ContextLife("Fragment")
    Fragment getContext();

	//首页
}
