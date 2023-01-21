import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class MySocketServer {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter server port: ");
        int portNumber = scanner.nextInt();
        ServerSocket serverSocket = new ServerSocket(portNumber);
        System.out.println("Server is running on " + portNumber + " port");
        while (true) {
            new SocketHandler(serverSocket.accept()).start();
        }
    }
}