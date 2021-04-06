package com.ea.vrtx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetSocket;

import java.util.HashSet;
import java.util.Set;

public class TCPExample extends AbstractVerticle {
    Set<NetSocket> clients = new HashSet<>();

    @Override
    public void start() throws Exception {
        NetServer server = vertx.createNetServer();
        server.connectHandler(socket -> {
            System.out.println("TCP Client connected "+ socket.remoteAddress());
            clients.add(socket);
            socket.closeHandler( v -> {
                System.out.println("TCP client disconnected  " + socket.remoteAddress());
                clients.remove(socket);
            });
        })
        .listen(7002, res -> {
            if (res.succeeded()) {
                System.out.println("TCP listening on port 7002");

                EventBus bus = vertx.eventBus();
                bus.consumer("example.news", this::handleMessage);
            } else {
                System.out.println("Failed to start the TCP server.");
            }
        });
    }

    private <T> void handleMessage(Message<T> tMessage) {
        vertx.executeBlocking(future -> {
            clients.forEach( client -> {
                client.write((String)tMessage.body());
            });
            future.complete();
        }, res -> {});
    }
}
