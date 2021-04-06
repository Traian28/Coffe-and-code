package com.ea.vrtx;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;

public class NewsVerticle extends AbstractVerticle {

    public void start(final Future<Void> future) {
        EventBus bus = vertx.eventBus();
        bus.consumer("example.news", this::handleMessage);

        future.complete();
    }

    private <T> void handleMessage(Message<T> tMessage) {
        System.out.println("Message received: " + tMessage.body());
    }
}
