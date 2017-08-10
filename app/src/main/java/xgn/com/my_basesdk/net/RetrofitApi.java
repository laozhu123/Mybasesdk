package xgn.com.my_basesdk.net;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import xgn.com.my_basesdk.net.responses.Helo;

/**
 * Created by huluzi on 2017/8/10.
 */

public interface RetrofitApi {

    /**
     * 重新申请骑士
     */
    @POST("task/doing")
    Observable<Helo> helo(
            @Header("Authorization")
                    String token,
            @Body
                    Object object);
}
