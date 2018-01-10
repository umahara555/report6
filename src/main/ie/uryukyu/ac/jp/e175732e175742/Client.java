package ie.uryukyu.ac.jp.e175732e175742;

import java.io.InputStreamReader; //入力ストリーム
import java.io.BufferedReader; //効率よく入力ストリームを処理するためのバッファ
import java.io.IOException;
import java.io.PrintWriter; //サーバへの出力ストリームを処理するライブラリ
import java.net.Socket; //ソケット(通信を行う際の端点)
import java.net.UnknownHostException;

public class Client {
    private Socket socket = null;
    private BufferedReader console = null;
    private PrintWriter streamOut = null;

    public Client(String serverName, int serverPort) {
        //サーバへの接続処理
        System.out.println("Establishing connection. Please wait ...");
        try {
            socket = new Socket(serverName, serverPort); //ソケット
            System.out.println("Connected: " + socket);
            start();
        } catch (UnknownHostException uhe) {
            System.out.println("Host unknown: " + uhe.getMessage());
        } catch (IOException ioe) {
            System.out.println("Unexpected exception: " + ioe.getMessage());
        }

        //接続したら、行単位でクライアント入力を読み込み、サーバへ書き込む。
        String line = "";
        while (!line.equals(".bye")){
            try {
                line = console.readLine(); //ターミナル上からの入力を行単位で読み込む
                streamOut.write(line + "\n"); //読み込んだ行を、改行文字追加してサーバへの出力ストリームに書き込む
                streamOut.flush(); //書き込みが終わるのを待つ
            } catch (IOException ioe) {
                System.out.println("Sending error: " + ioe.getMessage());
            }
        }
    }

    public void start() throws IOException {
        console = new BufferedReader(new InputStreamReader(System.in));
        streamOut = new PrintWriter(socket.getOutputStream(), true);
    }

    public void stop() {
        try {
            if (console   != null)  console.close();
            if (streamOut != null)  streamOut.close();
            if (socket    != null)  socket.close();
        } catch (IOException ioe) {
            System.out.println("Error closing ...");
        }
    }

    public static void main(String args[]) {
        Client client = null;
        if (args.length != 2)
            System.out.println("Usage: java ChatClient host port");
        else
            client = new Client(args[0], Integer.parseInt(args[1]));
    }
}
