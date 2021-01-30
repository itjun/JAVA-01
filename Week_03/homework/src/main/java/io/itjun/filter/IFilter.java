package io.itjun.filter;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * 过滤接口
 */
public interface IFilter {
    /**
     * <p> 消息之前过滤 </p>
     *
     * @param request 完整请求
     */
    FullHttpRequest before(FullHttpRequest request);

    /**
     * <p> 消息之后过滤 </p>
     *
     * @param request 完整请求
     */
    FullHttpRequest after(FullHttpRequest request);
}
