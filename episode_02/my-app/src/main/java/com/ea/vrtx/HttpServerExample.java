package com.ea.vrtx;


import io.vertx.core.Vertx;

public class HttpServerExample {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new HttpVerticle());
        vertx.deployVerticle(new ServiceVerticler());
        vertx.deployVerticle(new NewsVerticle());
        vertx.deployVerticle(new TCPExample());
    }

}
