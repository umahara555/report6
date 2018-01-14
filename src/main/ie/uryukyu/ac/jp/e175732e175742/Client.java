package ie.uryukyu.ac.jp.e175732e175742;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

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
            System.out.println("Connected: " + socket);

            streamIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            streamOut = new PrintWriter(socket.getOutputStream(), true);

        } catch (UnknownHostException uhe) {
            System.out.println("Host unknown: " + uhe.getMessage());
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

    public void output(String line){
        streamOut.println(line);
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
