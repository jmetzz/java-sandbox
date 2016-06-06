package com.github.jmetzz.frameworksLab.logging.utils_logging._2_handlers_demo.socket;

import javax.net.ServerSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class LoggingServer {
    private ServerSocket serverSocket = null;

    private Socket socket = null;

    public LoggingServer(int port) {
        ServerSocketFactory serverSocketFactory = ServerSocketFactory.getDefault();
        try {
            serverSocket = serverSocketFactory.createServerSocket(port);
            // Listens for a connection to be made to this socket and accepts it.
            // The method blocks until a connection is made
            socket = serverSocket.accept();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.err.println("Unable to create server");
            System.exit(-1);
        }
        System.out.printf("LogServer running on port: %s%n", port);
    }

    public void acceptMessage() {
        try {
            InputStream inStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
            String str = null;
            while ((str = reader.readLine()) != null) {
                System.out.println(str);
            }
        } catch (IOException ioe) {
            // ioe.printStackTrace();
            // Just handle next request.
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    public static void main(String args[]) {
        LoggingServer server = new LoggingServer(9999);
        server.acceptMessage();
    }
}