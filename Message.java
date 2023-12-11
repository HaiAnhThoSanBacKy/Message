import java.util.Scanner;
//tao mang
class Stack {
    private int maxSize;
    private String[] stackArray;
    private int top;
// constructor
    public Stack(int size) {
        maxSize = size;
        stackArray = new String[maxSize];
        top = -1;
    }
// day vao stack
    public void push(String value) {
       //
       if (top < maxSize - 1) {
            stackArray[++top] = value;
        } else {
            System.out.println("Error: Stack overflow. Cannot push message: " + value);
        }
    }
// hien thi stack
    public String pop() {
        if (top >= 0) {  //
            return stackArray[top--];                                           
        } else {
            System.out.println("Error: Stack underflow. Stack is empty.");    
            return null;
        }
    }
//ktra 
    public boolean isEmpty() {
        return top == -1;
    }
}

class Queue { //tao mang queue
    private int maxSize;
    private String[] queueArray;      
    private int front;
    private int rear;
    private int size;
    //constructor
    public Queue(int size) {
        maxSize = size;                    
        queueArray = new String[maxSize];
        front = 0;                             
        rear = -1;                              
        size = 0;                               
    }
//enterqueue
    public void enqueue(String value) {
        if (size < maxSize) {                         
            if (rear == maxSize - 1) {                 
                rear = -1;
            }
            queueArray[++rear] = value;
            size++;
        } else {
            System.out.println("Error: Queue is full. Cannot enqueue message: " + value);  
        }
    }
//deletequeue
    public String dequeue() {
        if (size > 0) {                                               
            String temp = queueArray[front++];
            if (front == maxSize) {                                  
                front = 0;                                          
            }
            size--;
            return temp;
        } else {
            System.out.println("Error: Queue is empty. Cannot dequeue message.");  
            return null;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
//queue and stack
public class Message {
    public static void main(String[] args) {
        int maxSize = 10; 
        Queue queueClass = new Queue(maxSize);   
        Stack classStack = new Stack(maxSize);   
        Scanner scanner = new Scanner(System.in);  

        // them tin nhan tu ban phim
        System.out.println("Enter messages (press 'end' to stop the program):");
        String userInput;      
        while (!(userInput = scanner.nextLine()).equals("end")) {        
            if (userInput.length() <= 250) {
                queueClass.enqueue(userInput);
            } else {
                System.out.println("Error: Message exceeds 250 characters limit."
                        + " Please enter a shorter message.");
            }
        }

        // dequeue du lieu den khi empty
        while (!queueClass.isEmpty()) {              
            String message = queueClass.dequeue();
            if (message != null) {
                classStack.push(message);
            }
        }

        // lay du lieu den khi stack empty
        while (!classStack.isEmpty()) {
            System.out.println("Message in stack: " + classStack.pop());   
        }

        scanner.close();
    }
}