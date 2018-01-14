package ie.uryukyu.ac.jp.e175732e175742;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class Server {
    int port;
    ServerSocket serverSocket = null;
    Socket socket = null;
    BufferedReader streamIn = null;
    PrintWriter streamOut = null;

    public Server(int port){
        this.port = port;
    }

    public void start(){
        try {
            serverSocket = new ServerSocket(port);

            //クライアント接続待ち
            System.out.println("Waiting for a client ...");
            socket = serverSocket.accept();

            //接続確立->クライアントからの入力読み込みのための設定
            System.out.println("Client accepted: " + socket);

            streamIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            streamOut = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException ioe){
            System.out.println("Error: " + ioe.getMessage());
        }
    }

    public String input(){
        try {
            return streamIn.readLine();
        } catch (IOException ioe){
            System.out.println("Error: " + ioe.getMessage());
            return "pass";
        }
    }

    public void  output(String line){
        streamOut.println(line);
    }

    public void close(){
        try {
            if (streamIn   != null)  streamIn.close();
            if (streamOut != null)  streamOut.close();
            if (socket    != null)  socket.close();
        } catch (IOException ioe) {
            System.out.println("Error closing ...");
        }
    }

}
