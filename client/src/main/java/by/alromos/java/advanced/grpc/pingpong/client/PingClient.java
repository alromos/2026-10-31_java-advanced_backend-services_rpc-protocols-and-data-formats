package by.alromos.java.advanced.grpc.pingpong.client;

import by.alromos.java.advanced.grpc.pingpong.PingRequest;
import by.alromos.java.advanced.grpc.pingpong.PingResponse;
import by.alromos.java.advanced.grpc.pingpong.PingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

public class PingClient {

  static void main(String[] args) throws InterruptedException {
    String target = args.length > 0 ? args[0] : "localhost:8080";

    ManagedChannel channel = ManagedChannelBuilder.forTarget(target)
        .usePlaintext()
        .build();
    try {
      PingServiceGrpc.PingServiceBlockingStub stub = PingServiceGrpc.newBlockingStub(channel);
      PingResponse response = stub.ping(PingRequest.newBuilder().setMessage("Ping").build());
      System.out.println("Response: " + response.getMessage());
    } finally {
      channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
    }
  }

}
