package com.sistema.cliente;

import java.io.IOException;
import java.util.Scanner;
import java.net.Socket;

public class ChatClient implements Runnable {

    public ChatClient() {
        scanner = new Scanner(System.in);
    }

    private final String serverAddress = "127.0.0.1";
    private ClientSocket clientSocket;
    private Scanner scanner;

    @Override
    public void run() {
        String message;
        while ((message = clientSocket.getMessage()) != null)
            System.out.println("Mensagem recebida de " + message);
    }

    public void startClient() throws IOException {
        this.clientSocket = new ClientSocket(
                new Socket(this.serverAddress, 5000));

        System.out.println("Cliente conectado no servidor: " + this.clientSocket.getRemoteSocketAddress());

        new Thread(this).start();
        this.messageLoop();
    }

    private void messageLoop() throws IOException {
        String message = "";
        System.out.println("Você está conectado no servidor, comece a usar o chat!\n/sair : sair do chat");

        do {
            message = scanner.nextLine();
            clientSocket.sendMessage(message);

        } while (!message.equals("/sair"));

    }
}
