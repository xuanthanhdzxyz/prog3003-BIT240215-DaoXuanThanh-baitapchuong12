package com.example.bai2;

import java.net.URI;
import java.util.function.Consumer;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class ChatClient extends WebSocketClient {
    private final Consumer<String> onMessageReceived;
    private final Consumer<String> onStatusChanged;

    public ChatClient(URI serverUri, Consumer<String> onMessageReceived, Consumer<String> onStatusChanged) {
        super(serverUri);
        this.onMessageReceived = onMessageReceived;
        this.onStatusChanged = onStatusChanged;
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        onStatusChanged.accept("Đã kết nối tới server");
    }

    @Override
    public void onMessage(String message) {
        onMessageReceived.accept(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        onStatusChanged.accept("Đã ngắt kết nối");
    }

    @Override
    public void onError(Exception ex) {
        onStatusChanged.accept("Lỗi kết nối: " + ex.getMessage());
    }
}
