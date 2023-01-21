import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketHandler extends Thread {

    private final BufferedReader reader;

    private final PrintWriter writer;

    public SocketHandler(Socket socket) {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
//            int symbol;
//            StringBuilder builder = new StringBuilder();
//            while ((symbol=reader.read())!=-1){
//                if (symbol=='\n'){
//                    System.out.println(builder);
//                    Scanner scanner = new Scanner(System.in);
//                    System.out.print("Enter your message: ");
//                    String sendMessage = scanner.nextLine();
//                    writer.println(sendMessage);
//                    writer.flush();
//                    break;
//                }
//                builder.append((char) symbol);
//            }
            int symbol;
            StringBuilder builder = new StringBuilder();
            while (true) {
                if ((symbol=reader.read()) == '\n') {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Other person message: "+builder);
                    System.out.print("Enter your message: ");
                    String sendMessage = scanner.nextLine();
                    writer.println(sendMessage);
                    writer.flush();
                    System.out.println("✔️✔️");
                    break;
                } else {
                    builder.append((char) symbol);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
