# Java NetBeans Maven - Lambda + JavaFX WebSocket Chat

## Yêu cầu môi trường
- JDK 17 trở lên
- NetBeans có hỗ trợ Maven
- Maven tự tải dependency khi mở project

## Cấu trúc bài

### Bài 1
Package: `com.example.bai1`
- `MathOperation.java`
- `LambdaDemo.java`

Chạy file:
- `LambdaDemo.java`

### Bài 2
Package: `com.example.bai2`
- `ChatWebSocketServer.java`
- `ChatServerMain.java`
- `ChatClient.java`
- `ChatClientLauncher.java`
- `ChatClientApp.java`

## Cách chạy Bài 2 trên NetBeans
### Bước 1: chạy server
- Chuột phải `ChatServerMain.java`
- Chọn **Run File**
- Console hiện: `WebSocket Server đang chạy tại ws://localhost:8887`

### Bước 2: chạy client thứ nhất
- Chuột phải `ChatClientLauncher.java`
- Chọn **Run File**
- Nhấn nút **Kết nối**

### Bước 3: chạy client thứ hai
- Chạy thêm một lần nữa `ChatClientLauncher.java`
- Nhấn **Kết nối**

### Bước 4: chat giữa 2 người
- Nhập tên
- Nhập nội dung
- Nhấn **Gửi**

## Gợi ý nộp GitHub
Up các file sau:
- `pom.xml`
- `src/`
- `README.md`

Không cần up:
- `target/`
- file `.class`

## Lưu ý
- Bài 2 dùng **WebSocket thật** qua thư viện `Java-WebSocket`
- Giao diện làm bằng **JavaFX**
- Chat trong mạng local qua địa chỉ `ws://localhost:8887`
