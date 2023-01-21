import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter server host: ");
        String serverHost = scanner.nextLine();
        System.out.print("Enter server port: ");
        scanner = new Scanner(System.in);
        int serverPort = scanner.nextInt();
        sendMessageServer(serverHost, serverPort);
    }

    public static void sendMessageServer(String serverHost, int serverPort) {
        try {
            Socket socket = new Socket(serverHost, serverPort);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter message: ");
            String sendMessage = scanner.nextLine();
            writer.println(sendMessage);
            writer.flush();
            System.out.println("✔️✔️");
            int symbol;
            StringBuilder builder = new StringBuilder();
            while (true) {
                if ((symbol = reader.read()) == '\n') {
                    System.out.println("Other person message: " + builder);
                    builder = new StringBuilder();
                    System.out.print("Enter message: ");
                    scanner = new Scanner(System.in);
                    sendMessage = scanner.nextLine();
                    socket = new Socket(serverHost, serverPort);
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    writer = new PrintWriter(socket.getOutputStream());
                    writer.println(sendMessage);
                    writer.flush();
                    System.out.println("✔️✔️");
                } else {
                    builder.append((char) symbol);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}