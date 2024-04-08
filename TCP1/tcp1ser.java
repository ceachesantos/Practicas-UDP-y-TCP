import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;

public class tcp1ser {
    
    private static ServerSocket server;
    private static int port = 0;
    static int Acum=0;
    
    public static void main(String args[]) throws IOException, ClassNotFoundException{
        if(args.length != 1){
            System.out.println("Uso correcto: tcp1ser port_number");
            return;
        }
        port = Integer.parseInt(args[0]);
        //create the socket server object
        server = new ServerSocket(port);
        //keep listens indefinitely until receives 'exit' call or program terminates

        while(true){
            //System.out.println("Servidor TCP, esperando por el cliente");
            Socket socket = server.accept();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            
            try {
                String sentence = (String) ois.readObject();
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
            } catch (Exception e) {
                Acum = 0;
            }
            
            try {
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject("Acumulado: "+Acum); //close resources
                ois.close();
                oos.close();
                socket.close();
            } catch (Exception e) {
                
            }

        }
        
    }
    
}
