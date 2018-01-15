package ie.uryukyu.ac.jp.e175732e175742;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

/**
 * サーバー
 */
public class Server {
    private int port;
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private BufferedReader streamIn = null;
    private PrintWriter streamOut = null;

    /**
     * コンストラクタ
     *
     * @param port 通信に使用するポート番号を指定．
     */
    public Server(int port){
        this.port = port;
    }

    /**
     * 通信を開始する．
     */
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
    /**
     * 受信をする．
     */
    public String input(){
        try {
            return streamIn.readLine();
        } catch (IOException ioe){
            System.out.println("Error: " + ioe.getMessage());
            return "pass";
        }
    }
    /**
     * 送信する．
     *
     * @param line 送信したい文字列
     */
    public void  output(String line){
        streamOut.println(line);
    }

    /**
     * 通信を終了．
     */
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
