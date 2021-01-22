package io.itjun.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketDemo1 extends SocketDemo {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8101);
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                service(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
