package com.example.bai2;

public class ChatServerMain {
    public static final int PORT = 8887;

    public static void main(String[] args) throws Exception {
        ChatWebSocketServer server = new ChatWebSocketServer(PORT);
        server.start();
        System.out.println("Nhấn Ctrl + C để dừng server.");
    }
}
