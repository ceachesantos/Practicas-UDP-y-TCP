import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.net.*;
import java.io.*;

public class tcp2cli {
    public static void main(String[] args) throws InterruptedException {

        Socket socketCliente = null;
        BufferedReader input = null;
        PrintWriter servOutput = null;

        if (args.length != 2) {
            System.out.println("Uso correcto: tcp2cli ip_address port_number");
            return;
        }

        try {

            int puerto = Integer.parseInt(args[1]);
            InetAddress IP = InetAddress.getByName(args[0]);
            
            socketCliente = new Socket(IP, puerto);
            //socketCliente = new Socket();
            //socketCliente.setSoTimeout(15000);
            //try {
            //    socketCliente.connect(new InetSocketAddress(IP, puerto), 15000);
            //} catch (Exception e) {
            //    System.out.println("Servidor no disponible");
            //    socketCliente.close();
            //    return;
            //}
            input = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            servOutput = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())), true);
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

            String cadena;

            while (true) {

                System.out.print("Introduce una cadena de enteros: ");
  
                cadena = inFromUser.readLine();
                if (cadena.trim().equals("0")) {
                    socketCliente.close();
                    return;
                }

                if (cadena.trim().equals("0")) {
                    socketCliente.close();
                    return;
                }

                servOutput.println(cadena);
                cadena = input.readLine();
                System.out.println("Acumulador: " + cadena);

            }

        //} catch (java.net.SocketTimeoutException e) {
        //    System.out.println("Timeout de 15 segundos");               
        } 
        catch (Exception e) {
            TimeUnit.SECONDS.sleep(15);
            System.out.println("Timeout de 15 segundos");
        }

    }
}
