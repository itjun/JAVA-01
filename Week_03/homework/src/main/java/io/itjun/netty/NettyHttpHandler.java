package io.itjun.netty;

import io.itjun.config.FilterConfig;
import io.itjun.config.RouterConfig;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;

public class NettyHttpHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelReadComplete(ChannelHandlerContext context) {
        context.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext context, Object msg) {
        FullHttpRequest request = (FullHttpRequest) msg;
        FilterConfig filterConfig = new FilterConfig();
        filterConfig.before(request);
        new RouterConfig().config(request, context);
        filterConfig.after(request);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
        cause.printStackTrace();
        context.close();
    }
}
