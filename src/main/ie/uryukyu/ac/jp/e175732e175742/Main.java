package ie.uryukyu.ac.jp.e175732e175742;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int mode; // Server or Client
        int port = 25565;
        String serverName = "";
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        String line = "";
        try {
            System.out.println("起動モードを選択して下さい．");
            line = console.readLine();

            mode = Integer.parseInt(line);

            if (mode == 0) { //server
                System.out.println("サーバーモードで起動します．．．");
                Server server = new Server(port);
                server.run();
            } else if (mode == 1) { //client
                System.out.println("クライアントモードで起動します．．．");
                System.out.println("ホスト名を入力して下さい．");
                line = console.readLine();
                serverName = line;
                Client client = new Client(serverName, port);
                client.run();
            }

        } catch (IOException ioe){
            System.out.println("Error: " + ioe.getMessage());
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
