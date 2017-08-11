package xgn.com.my_basesdk.net;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import xgn.com.basesdk.network.model.BaseResponse;

/**
 * Created by huluzi on 2017/8/11.
 */

public class TokenInterceptor implements Interceptor {

    //    private static String format = "\"{\"password\":\"%s\", \"phone\":\"%s\"}\"";
    public static final String TOKEN_INVALID = "101021";
    private Gson mGson = new Gson();
    //    public static final String ACCOUNT_FROZEN = "101011";
    private volatile String mToken;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        /**
         * 改方法会耗尽输出流、故只能执行一次
         */
        String bodyString = response.body().string();

        BaseResponse baseResponse = mGson.fromJson(bodyString, BaseResponse.class);
        if (baseResponse != null) {
            // Token 过期
            if (TOKEN_INVALID.equals(baseResponse.resultCode)) {
                // 再次进行网络一模一样的网路请求
                if (true) {
                    request = request.newBuilder()
                            .header("Authorization", mToken)
                            .build();
                    response = chain.proceed(request);
                } else {
                    response = duplicate(response, bodyString);
                }
            } else {
                response = duplicate(response, bodyString);
            }
        } else {
            response = duplicate(response, bodyString);
        }

        return response;
    }

    /**
     * 重新构造相同的返回
     */
    private Response duplicate(Response response, String bodyString) {
        return response.newBuilder()
                .headers(response.headers())
                .body(ResponseBody.create(response.body().contentType(), bodyString))
                .build();
    }
}

