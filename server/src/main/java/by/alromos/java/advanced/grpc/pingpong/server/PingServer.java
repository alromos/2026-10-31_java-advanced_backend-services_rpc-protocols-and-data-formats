package by.alromos.java.advanced.grpc.pingpong.server;

import by.alromos.java.advanced.grpc.pingpong.PingRequest;
import by.alromos.java.advanced.grpc.pingpong.PingResponse;
import by.alromos.java.advanced.grpc.pingpong.PingServiceGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

public class PingServer {

  private static final int PORT = 8080;

  static void main(String[] args) throws IOException, InterruptedException {
    Server server = ServerBuilder.forPort(PORT)
        .addService(new PingServiceImpl())
        .build()
        .start();
    System.out.println("gRPC server listening on port " + PORT);

    Runtime.getRuntime().addShutdownHook(new Thread(server::shutdown));
    server.awaitTermination();
  }

  static class PingServiceImpl extends PingServiceGrpc.PingServiceImplBase {

    @Override
    public void ping(PingRequest request, StreamObserver<PingResponse> responseObserver) {
      System.out.println("Received: " + request.getMessage());
      responseObserver.onNext(PingResponse.newBuilder().setMessage("Pong").build());
      responseObserver.onCompleted();
    }
  }

}
