package io.kimmking.rpcfx.client;

import com.alibaba.fastjson.JSON;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Request;
import org.asynchttpclient.Response;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.asynchttpclient.Dsl.*;

/**
 * @author: peixinyi
 * @version: V1.0.0.0
 * @date: 2021-03-22 15:21
 */
public class NettyClient implements RpcClient {

    @Override
    public RpcfxResponse post(RpcfxRequest req, String url) throws IOException {
        AsyncHttpClient asyncHttpClient = asyncHttpClient();
        Request request = org.asynchttpclient.Dsl.post(url)
                .setHeaders(new DefaultHttpHeaders().set("Content-Type", "application/json;charset=UTF-8"))
                .setBody(JSON.toJSONString(req)).build();

        Future<Response> whenResponse = asyncHttpClient.executeRequest(request);

        RpcfxResponse rpcfxResponse = new RpcfxResponse();
        Response response = null;
        try {
            response = whenResponse.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            rpcfxResponse.setStatus(false);
            rpcfxResponse.setResult(null);
            rpcfxResponse.setException(e);

            return rpcfxResponse;
        }
        asyncHttpClient.close();
        return JSON.parseObject(response.getResponseBody(), RpcfxResponse.class);
    }
}
