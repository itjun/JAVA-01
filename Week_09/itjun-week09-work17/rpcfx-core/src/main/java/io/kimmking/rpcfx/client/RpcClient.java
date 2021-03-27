package io.kimmking.rpcfx.client;

import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;

import java.io.IOException;

/**
 * @author: peixinyi
 * @version: V1.0.0.0
 * @date: 2021-03-22 15:26
 */
public interface RpcClient {
    public RpcfxResponse post(RpcfxRequest req, String url) throws IOException;
}
