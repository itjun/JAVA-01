package io.itjun.router;

import io.itjun.router.load.WeightAddress;
import io.itjun.utils.HttpClientTool;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * 路由器接口
 */
public interface IRouter {
    /**
     * <p> 解析uri的请求标记，根据标记调用不同的路由器 </p>
     */
    default void route(FullHttpRequest request, ChannelHandlerContext context) {
        String path = request.uri();
        System.out.println("当前channel信息： " + context.channel());
        if (isRemovePrefix()) {
            path = request.uri().substring(getPrefix().length());
        }
        String requestUrl = LoadBalancing.round(getAddress()) + "/" + path;
        write(request, context, HttpClientTool.invoking(request.method().name(), requestUrl), HttpResponseStatus.OK);
    }

    default void write(FullHttpRequest request, ChannelHandlerContext context, String value, HttpResponseStatus status) {
        FullHttpResponse response = null;
        try {
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(value.getBytes(StandardCharsets.UTF_8)));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", response.content().readableBytes());
            context.write(response);
        } catch (Exception e) {
            System.out.println("处理出错:" + e.getMessage());
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
            context.write(response);
        } finally {
            if (request != null) {
                if (!HttpUtil.isKeepAlive(request)) {
                    context.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
                    response.headers().set(CONNECTION, KEEP_ALIVE);
                    context.write(response);
                }
            }
        }
    }

    /**
     * <p> 路由标记 </p>
     */
    String getPrefix();

    /**
     * <p> 是否删除前缀 </p>
     */
    boolean isRemovePrefix();

    List<String> getAddress();

    List<WeightAddress> getWeightAddress();
}
