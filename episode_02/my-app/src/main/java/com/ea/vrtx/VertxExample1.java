package com.ea.vrtx;

import io.vertx.core.Vertx;

import java.util.concurrent.atomic.AtomicInteger;

public class VertxExample1 {
   public static void main(String []args) {
       final AtomicInteger eventId = new AtomicInteger(0);

       Vertx vertx = Vertx.vertx();
       vertx.setPeriodic(1000, id -> {
           eventId.incrementAndGet();
           System.out.println("Event received id=" + eventId.get());

           // Do later
           vertx.executeBlocking(future -> {
               String res = "Event id completed" + eventId.get();
               try {
                   Thread.sleep(2000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               future.complete(res);
           }, res -> {
               System.out.println(res.result());
           });

       });
   }
}
