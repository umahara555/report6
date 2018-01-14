package ie.uryukyu.ac.jp.e175732e175742;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        boolean turn = false;

        int mode; // Server or Client
        int port = 25565; //使用するポート番号
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
                server.start();

                while(!line.equals("bye")){
                    if (turn){
                        System.out.println("your turn");
                        line = console.readLine();
                        server.output(line);
                        turn = false;
                    } else {
                        System.out.println("client turn");
                        line = server.input();
                        System.out.println(line);
                        turn = true;
                    }
                }

                server.close();
            } else if (mode == 1) { //client
                System.out.println("クライアントモードで起動します．．．");
                System.out.println("ホスト名を入力して下さい．");
                line = console.readLine();
                serverName = line;
                Client client = new Client(serverName, port);
                client.start();

                while(!line.equals("bye")){
                    if (!turn){
                        System.out.println("your turn");
                        line = console.readLine();
                        client.output(line);
                        turn = true;
                    } else {
                        System.out.println("server turn");
                        line = client.input();
                        System.out.println(line);
                        turn = false;
                    }
                }

                client.stop();
            }

        } catch (IOException ioe){
            System.out.println("Error: " + ioe.getMessage());
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
