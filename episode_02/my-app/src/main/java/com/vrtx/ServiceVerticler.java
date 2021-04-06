package com.ea.vrtx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;


public class ServiceVerticler extends AbstractVerticle {
    private EventBus bus;

    @Override
    public void start(final Future<Void> future) {
        Router router = Router.router(vertx);
        router.get("/news").handler( ctx -> {
            ctx.response().putHeader("content-type", "application/json")
                    .end( "{ \"name\": \"newsapp\", \"version\": \"1\"}");
        });
        router.post("/news").handler(BodyHandler.create());
        router.post("/news").handler(this::handlePost);

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(7001, result -> {
                    if (result.succeeded()) {
                        System.out.println("Server started at port 7001");
                        future.complete();
                    } else {
                        future.fail(result.cause());
                    }
                });
        bus = vertx.eventBus();
    }

    private void handlePost(RoutingContext routingContext) {
        String newsItem = routingContext.getBodyAsString();
        bus.publish("example.news", newsItem);
        routingContext.response().setStatusCode(201)
                .end();
    }
}
