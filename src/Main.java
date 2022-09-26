import com.sistema.cliente.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ChatClient client = new ChatClient();

        try {
            client.startClient();
        } catch (IOException ex) {
            System.out.println("Erro ao iniciar o cliente: " + ex.getMessage());
        }

        System.out.println("O Cliente desconectou do servidor!");
    }
}
