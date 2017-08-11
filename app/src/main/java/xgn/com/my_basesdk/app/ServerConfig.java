package xgn.com.my_basesdk.app;

import xgn.com.basesdk.commonui.config.ConfigManager;

/**
 * Created by huluzi on 2017/8/11.
 */

public class ServerConfig {
    public static int getServerEnv(){
        int serverEnv = ConfigManager.getServerEnv();
        return serverEnv;
    }
}
