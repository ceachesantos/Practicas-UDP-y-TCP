import java.io.IOException;
import java.net.SocketException;
import java.net.*;
import java.io.*;

public class tcp2ser {

    public static void main(String[] args) {

        if(args.length != 1){
            System.out.println("Uso correcto: tcp2ser port_number");
            return;
        }

            String puerto_destino = args[0];
            int puerto = Integer.parseInt(puerto_destino);

            try {
                System.out.println("Servidor TCP multihilo");

                ServerSocket socketServidor = null;
                socketServidor = new ServerSocket(puerto);

                // Una vez entras aqui estás perdido
                while (true) {

                    Socket socketCliente = socketServidor.accept(); // Salta aquí la excepción
                    procesoHijo pH = new procesoHijo(socketCliente);
                    pH.start();

                } // Llave del while

            } catch (SocketException ex) {

            } catch (IOException ex) {

            }
    }

}

class procesoHijo extends Thread {

    Socket socketCliente;
    int Acum = 0;

    procesoHijo(Socket socket) {
        socketCliente = socket;
    }

    public void run() {
        System.out.println("Cliente nuevo, se reinicia Acum");

        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()))) {
            PrintWriter salida = new PrintWriter(
                    new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())), true);

            String mensaje = null;

            while ((mensaje = entrada.readLine()) != null) {

                System.out.println("Mensaje recibido: " + mensaje);
                String[] stringNumeros = mensaje.split(" ");

                for (int i = 0; i < stringNumeros.length; i++) {
                    //stringNumeros[i].replaceAll(" ", "");
                    Acum = Integer.parseInt(stringNumeros[i].trim()) + Acum;
                    System.out.println("Acumulador:" + Acum);

                }

                salida.println(Acum);
            }
        } catch (NumberFormatException | IOException e) {

        }

        try {
            socketCliente.close();
        } catch (IOException e) {
            System.out.println("Error el cerrar el socket");
        }
    }
}
