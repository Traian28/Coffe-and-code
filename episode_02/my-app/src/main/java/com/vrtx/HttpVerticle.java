package com.ea.vrtx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;


public class HttpVerticle extends AbstractVerticle {

    @Override
    public void start(final Future<Void> future) {
        vertx.createHttpServer()
                .requestHandler(this::handleRequest)
                .listen(7000, result -> {
                    if (result.succeeded()) {
                        System.out.println("Server started at port 7000");
                        future.complete();
                    } else {
                        future.fail(result.cause());
                    }
                });
    }

    private void handleRequest(HttpServerRequest httpServerRequest) {
        HttpServerResponse response = httpServerRequest.response();
        response.putHeader("content-type", "text/html")
                .end("<h1>Hello Social from Vert.X</h1>");
    }
}
