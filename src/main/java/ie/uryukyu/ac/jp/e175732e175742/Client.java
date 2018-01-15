package ie.uryukyu.ac.jp.e175732e175742;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * クライアント
 */
public class Client {
    private String serverName; //接続先のホスト名
    private int port; //ポート番号
    private Socket socket = null;
    private BufferedReader streamIn = null;
    private PrintWriter streamOut = null;

    /**
     * コンストラクタ
     *
     * @param serverName 接続先のホスト名
     * @param port 通信に使用するポート番号
     */
    public Client(String serverName ,int port){
        this.serverName = serverName;
        this.port = port;
    }

    /**
     * 通信を開始する．
     */
    public void start(){
        try {
            socket = new Socket(serverName, port);
            System.out.println("Connected: " + socket);

            streamIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            streamOut = new PrintWriter(socket.getOutputStream(), true);

        } catch (UnknownHostException uhe) {
            System.out.println("Host unknown: " + uhe.getMessage());
        } catch (IOException ioe){
            System.out.println("Error: " + ioe.getMessage());
        }
    }

    /**
     * 受信をする．．
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
    public void output(String line){
        streamOut.println(line);
    }

    /**
     * 通信の終了．
     */
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
