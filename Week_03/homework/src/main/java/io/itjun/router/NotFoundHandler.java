package io.itjun.router;

import io.itjun.router.load.WeightAddress;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.NOT_FOUND;
import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import static org.apache.http.HttpHeaders.CONNECTION;

public class NotFoundHandler implements IRouter {

    @Override
    public void route(FullHttpRequest request, ChannelHandlerContext context) {
        FullHttpResponse response = null;
        try {
            String value = "OOPS, 404 NOT_FOUND";
            response = new DefaultFullHttpResponse(HTTP_1_1, NOT_FOUND, Unpooled.wrappedBuffer(value.getBytes(StandardCharsets.UTF_8)));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", response.content().readableBytes());
        } catch (Exception e) {
            System.out.println("处理出错:" + e.getMessage());
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
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

    @Override
    public String getPrefix() {
        return null;
    }

    @Override
    public boolean isRemovePrefix() {
        return false;
    }

    @Override
    public List<String> getAddress() {
        return new ArrayList<>();
    }

    @Override
    public List<WeightAddress> getWeightAddress() {
        return new ArrayList<>();
    }

}
