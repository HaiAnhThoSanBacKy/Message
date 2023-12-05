import java.util.Scanner;

class MessageSystem {
    private static final int MAX_MESSAGE_LENGTH = 250;
    private Queue<String> messageQueue;
    private Stack<String> messageStack;

    public MessageSystem() {
        this.messageQueue = new Queue<>(5); // Kích thước hàng đợi mặc định là 5
        this.messageStack = new Stack<>(5); // Kích thước ngăn xếp mặc định là 5
    }

    public void interactWithUser() {
        Scanner scanner = new Scanner(System.in);

        try {
            for (int i = 0; i < 5; i++) {
                // Cho phép người dùng nhập tin nhắn và kiểm tra tính hợp lệ
                System.out.print("Enter a message (max 250 characters): ");
                String userMessage = scanner.nextLine();

                if (isValidMessage(userMessage)) {
                    // Nếu tin nhắn hợp lệ, thêm vào hàng đợi và hiển thị thông báo
                    enqueueMessage(userMessage);
                    System.out.println("Message sent successfully!");
                } else {
                    // Nếu tin nhắn không hợp lệ, hiển thị thông báo lỗi
                    throw new IllegalArgumentException("Error: Invalid message. Message not sent.");
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private void enqueueMessage(String message) {
        messageQueue.enqueue(message);
        System.out.println("Enqueued message: " + message);
    }

    public void processMessages() {
        try {
            while (!messageQueue.isEmpty()) {
                String message = messageQueue.dequeue();
                pushToStack(message);
                System.out.println("Processing message: " + message);
            }
        } catch (QueueEmptyException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void pushToStack(String message) {
        try {
            messageStack.push(message);
        } catch (StackOverflowException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void displayProcessedMessages() {
        try {
            System.out.println("Processed messages:");
            while (!messageStack.isEmpty()) {
                String processedMessage = messageStack.pop();
                System.out.println(processedMessage);
            }
        } catch (StackEmptyException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private boolean isValidMessage(String message) {
        if (message == null || message.trim().isEmpty()) {
            return false;
        }

        return message.length() <= MAX_MESSAGE_LENGTH;
    }
}

// (Các class và ngoại lệ khác giữ nguyên)

public class MessageSystemApp {
    public static void main(String[] args) {
        MessageSystem messageSystem = new MessageSystem();

        // Thực hiện thử nghiệm với người dùng nhập và gửi tin nhắn
        messageSystem.interactWithUser();

        // Xử lý và hiển thị tin nhắn đã xử lý
        messageSystem.processMessages();
        messageSystem.displayProcessedMessages();
    }
}