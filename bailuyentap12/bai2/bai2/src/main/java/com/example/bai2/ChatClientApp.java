package com.example.bai2;

import java.net.URI;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChatClientApp extends Application {
    private ChatClient client;
    private TextArea chatArea;
    private TextField nameField;
    private TextField messageField;
    private Label statusLabel;

    @Override
    public void start(Stage stage) {
        chatArea = new TextArea();
        chatArea.setEditable(false);
        chatArea.setWrapText(true);
        chatArea.setPrefHeight(350);

        nameField = new TextField();
        nameField.setPromptText("Nhập tên của bạn");
        nameField.setText("User" + (int) (Math.random() * 100));

        messageField = new TextField();
        messageField.setPromptText("Nhập tin nhắn...");

        statusLabel = new Label("Chưa kết nối");

        Button connectButton = new Button("Kết nối");
        Button sendButton = new Button("Gửi");
        Button disconnectButton = new Button("Ngắt kết nối");

        connectButton.setOnAction(e -> connectToServer());
        sendButton.setOnAction(e -> sendMessage());
        disconnectButton.setOnAction(e -> disconnect());

        messageField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                sendMessage();
            }
        });

        HBox topBox = new HBox(10, new Label("Tên:"), nameField, connectButton, disconnectButton);
        topBox.setAlignment(Pos.CENTER_LEFT);

        HBox bottomBox = new HBox(10, messageField, sendButton);
        bottomBox.setAlignment(Pos.CENTER);

        VBox centerBox = new VBox(10, new Label("Khung chat"), chatArea, statusLabel);

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15));
        root.setTop(topBox);
        root.setCenter(centerBox);
        root.setBottom(bottomBox);

        Scene scene = new Scene(root, 620, 500);
        stage.setTitle("Bài 2 - JavaFX WebSocket Chat Local");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> disconnect());
    }

    private void connectToServer() {
        try {
            if (client != null && client.isOpen()) {
                showInfo("Bạn đã kết nối rồi.");
                return;
            }

            client = new ChatClient(
                    new URI("ws://localhost:" + ChatServerMain.PORT),
                    message -> Platform.runLater(() -> chatArea.appendText(message + "\n")),
                    status -> Platform.runLater(() -> statusLabel.setText(status))
            );
            client.connect();
        } catch (Exception ex) {
            showError("Không kết nối được tới server: " + ex.getMessage());
        }
    }

    private void sendMessage() {
        String sender = nameField.getText().trim();
        String message = messageField.getText().trim();

        if (sender.isEmpty()) {
            showInfo("Vui lòng nhập tên trước khi chat.");
            return;
        }

        if (client == null || !client.isOpen()) {
            showInfo("Bạn chưa kết nối tới server.");
            return;
        }

        if (message.isEmpty()) {
            return;
        }

        client.send(sender + ": " + message);
        messageField.clear();
    }

    private void disconnect() {
        try {
            if (client != null) {
                client.close();
            }
        } catch (Exception ignored) {
        }
    }

    private void showInfo(String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showError(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
