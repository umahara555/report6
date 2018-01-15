package ie.uryukyu.ac.jp.e175732e175742;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        TicTacToe ttt;

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

                ttt = new TicTacToe();
                ttt.print();
                int[] xy;
                while(!line.equals("bye")){
                    if (ttt.isTurn()){
                        System.out.println("your turn");
                        line = console.readLine();
                        server.output(line);
                        xy = lol(line);
                        ttt.handCircle(xy[0], xy[1]);
                        if(judge(ttt)){ break;}
                    } else {
                        System.out.println("client turn");
                        line = server.input();
                        System.out.println(line);
                        xy = lol(line);
                        ttt.handCross(xy[0], xy[1]);
                        if(judge(ttt)){ break;}
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

                ttt = new TicTacToe();
//                ttt.setTurn(!ttt.isTurn());
                ttt.print();
                int[] xy;
                while(!line.equals("bye")){
                    if (!ttt.isTurn()){
                        System.out.println("your turn");
                        line = console.readLine();
                        client.output(line);
                        xy = lol(line);
                        ttt.handCross(xy[0], xy[1]);
                        if(judge(ttt)){ break;}
                    } else {
                        System.out.println("server turn");
                        line = client.input();
                        System.out.println(line);
                        xy = lol(line);
                        ttt.handCircle(xy[0], xy[1]);
                        if(judge(ttt)){ break;}
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

    public static int[] lol(String data){
        int[] coordinates = new int[2];
        coordinates[0] = Integer.parseInt(data.substring(0,1));
        coordinates[1] = Integer.parseInt(data.substring(1,2));
        return coordinates;
    }

    public static boolean judge(TicTacToe ttt){
        char result = ttt.judge_winner();
        if (result != 'e'){
            System.out.println("Winner :" + result);
            return true;
        }else{
            return false;
        }
    }
}
