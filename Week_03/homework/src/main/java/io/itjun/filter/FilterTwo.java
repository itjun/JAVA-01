package io.itjun.filter;

import io.netty.handler.codec.http.FullHttpRequest;

public class FilterTwo implements IFilter {

    public FilterTwo() {
    }

    @Override
    public FullHttpRequest before(FullHttpRequest request) {
        System.out.println("FilterTwo - Before: " + request.uri());
        return request;
    }

    @Override
    public FullHttpRequest after(FullHttpRequest request) {
        System.out.println("FilterTwo - After: " + request.uri());
        return request;
    }
}
