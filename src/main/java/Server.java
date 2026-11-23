import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final Integer PORT = 8080;

    static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен на netology.homework: " + PORT);

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("New connection accepted, порт клиента: " + clientSocket.getPort());

                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream()));

                    // Шаг 1: спрашиваем имя
                    out.println("Write your name");
                    String name = in.readLine();
                    System.out.println("Клиент прислал имя: " + name);

                    // Шаг 2: спрашиваем возраст
                    out.println("Are you child? (yes/no)");
                    String answer = in.readLine();
                    System.out.println("Клиент ответил: " + answer.equalsIgnoreCase("yes/no"));

                    // Шаг 3: итоговое приветствие по ответу
                    if ("yes".equalsIgnoreCase(answer)) {
                        out.println("Welcome to the kids area, " + name + "! Let's play!");
                    } else {
                        out.println("Welcome to the adult zone, " + name + "! Have a good rest, or a good working day!");
                    }

                    //out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));
                    //System.out.printf("Клиент прислал: %s (порт клиента: %d)%n", name, clientSocket.getPort());
                    System.out.println("Диалог завершён");

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
            }
        }
    }