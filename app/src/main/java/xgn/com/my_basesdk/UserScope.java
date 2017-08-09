package xgn.com.my_basesdk;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Administrator on 2017/8/9.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface UserScope {
}
