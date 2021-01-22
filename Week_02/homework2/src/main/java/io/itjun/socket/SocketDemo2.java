package io.itjun.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketDemo2 extends SocketDemo {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8101);
        while (true) {
            try {
                final Socket socket = serverSocket.accept();
                new Thread(() -> {
                    service(socket);
                }).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
