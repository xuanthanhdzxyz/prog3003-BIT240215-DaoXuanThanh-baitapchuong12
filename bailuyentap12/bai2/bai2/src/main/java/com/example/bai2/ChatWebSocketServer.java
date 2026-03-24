package com.example.bai2;

import java.net.InetSocketAddress;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class ChatWebSocketServer extends WebSocketServer {
    private final Set<WebSocket> clients = ConcurrentHashMap.newKeySet();

    public ChatWebSocketServer(int port) {
        super(new InetSocketAddress(port));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        clients.add(conn);
        System.out.println("Client đã kết nối: " + conn.getRemoteSocketAddress());
        conn.send("[Hệ thống] Kết nối thành công tới server.");
        broadcastExcept(conn, "[Hệ thống] Có người dùng vừa tham gia đoạn chat.");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        clients.remove(conn);
        System.out.println("Client đã ngắt kết nối: " + conn.getRemoteSocketAddress());
        broadcast("[Hệ thống] Một người dùng đã rời đoạn chat.");
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("Tin nhắn nhận được: " + message);
        broadcast(message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.err.println("Lỗi server: " + ex.getMessage());
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        System.out.println("WebSocket Server đang chạy tại ws://localhost:" + getPort());
        setConnectionLostTimeout(60);
    }

    private void broadcastExcept(WebSocket excluded, String message) {
        for (WebSocket client : clients) {
            if (client != excluded && client.isOpen()) {
                client.send(message);
            }
        }
    }
}
