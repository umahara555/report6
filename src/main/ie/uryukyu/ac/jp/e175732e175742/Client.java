package ie.uryukyu.ac.jp.e175732e175742;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    String serverName;
    int port;

    Socket socket = null;
    BufferedReader streamIn = null;
    PrintWriter streamOut = null;

    public Client(String serverName ,int port){
        this.serverName = serverName;
        this.port = port;
    }

    public void start(){
        try {
            socket = new Socket(serverName, port);

            streamIn = new BufferedReader(new InputStreamReader(System.in));
            streamOut = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException ioe){
            System.out.println("Error: " + ioe.getMessage());
        }
    }

    public String input(){
        String line = ""; //入力受け取り
        try {
            line = streamIn.readLine();
        } catch (IOException ioe){
            System.out.println("Error: " + ioe.getMessage());
        }

        return line;
    }

    public void output(String line){
        streamOut.write(line); //読み込んだ行をサーバへの出力ストリームに書き込む
        streamOut.flush(); //書き込みが終わるのを待つ
    }

    public void stop() {
        try {
            if (streamIn   != null)  streamIn.close();
            if (streamOut != null)  streamOut.close();
            if (socket    != null)  socket.close();
        } catch (IOException ioe) {
            System.out.println("Error closing ...");
        }
    }
}
