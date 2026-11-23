import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    static void main(String[] args) {
        String host = "netology.homework";

        try (Socket clientSocket = new Socket(host, Server.PORT)) {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            // Чат из сети
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            // Чат из консоли
            BufferedReader console = new BufferedReader(
                    new InputStreamReader(System.in));

            // Шаг 1: получаем вопрос, отвечаем
            System.out.println("Сервер: " + in.readLine());
            String name = console.readLine();
            out.println(name);

            // Шаг 2 пишем возраст
            System.out.println("Сервер: " + in.readLine());
            String answer = console.readLine();
            out.println(answer);

            // Шаг 3: финальное приветствие
            System.out.println("Сервер: " + in.readLine());

            // System.out.println("Ответ сервера: " + in.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
