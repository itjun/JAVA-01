package io.itjun.filter;

import io.netty.handler.codec.http.FullHttpRequest;

public class FilterOne implements IFilter {

    public FilterOne() {
    }

    @Override
    public FullHttpRequest before(FullHttpRequest request) {
        System.out.println("FilterOne - Before: " + request.uri() + "\tMethod:" + request.method().name());
        return request;
    }

    @Override
    public FullHttpRequest after(FullHttpRequest request) {
        System.out.println("FilterOne - After: " + request.uri());
        return request;
    }
}
