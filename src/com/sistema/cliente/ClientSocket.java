package com.sistema.cliente;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

public class ClientSocket {
    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;

    public ClientSocket(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(this.socket.getOutputStream(), true);
    }

    public String getMessage() {
        try {
            return in.readLine();
        } catch (IOException ex) {
            return null;
        }
    }

    public SocketAddress getRemoteSocketAddress() {
        return socket.getRemoteSocketAddress();
    }

    public boolean sendMessage(String message) {
        out.println(message);
        return !out.checkError();
    }

    public void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println("Erro ao fechar o socket: " + ex.getMessage());
        }
    }

    public SocketChannel getChannel() {
        return socket.getChannel();
    }
}
