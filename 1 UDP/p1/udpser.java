import java.io.*;
import java.net.*;
import java.util.concurrent.ArrayBlockingQueue;

class udpser {
    public static void main(String args[]) throws Exception {
        if(args.length != 1){
            System.out.println("Uso correcto: udpser port_numer");
            return;
        }

        System.out.println("Iniciado el servidor UDP");
        int puerto = Integer.parseInt(args[0]);
        //System.out.println(puerto);
        DatagramSocket serverSocket = new DatagramSocket(puerto);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        int Acum=0;
        
        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData());
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            puerto = port;
            //System.out.println(port);
            //String capitalizedSentence = sentence.toUpperCase();

                sentence.trim();
                //sentence.replaceAll("\\s+", " ");
                System.out.println("Mensaje recibido: " + sentence);
                String[] StringNumeros = sentence.split(" ");
                int[] numeros = new int[1024];

                for (int i = 0; i < StringNumeros.length; i++) {
                    try {
                            int aux = Integer.parseInt(StringNumeros[i].trim());
                            if(aux == 0) break;
                            numeros[i] = Integer.parseInt(StringNumeros[i].trim());
                            Acum = numeros[i] + Acum;
                            System.out.println("Acumulado: " + Acum);
                    } catch (Exception e) {
                        //System.out.println("salto espacio");
                }
                
                   
                }
 
                sendData = String.valueOf(Acum).getBytes();

            //sendData = capitalizedSentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, puerto);
            serverSocket.send(sendPacket);
        }
    }
}