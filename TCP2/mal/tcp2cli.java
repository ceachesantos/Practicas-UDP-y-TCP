import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class tcp2cli {

    public static void main(String[] args)
            throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
        try {
            if (args.length != 2) {
                System.out.println("Uso correcto: tcp2cli ip_address port_number");
                return;
            }
            boolean terminar = false;
            boolean terminar2 = false;
            String IP = args[0];
            int port = Integer.parseInt(args[1]);
            while (terminar == false) {
                // get the localhost IP address, if server is running on some other IP, you need
                // to use that
                // InetAddress host = InetAddress.getLocalHost();
                InetAddress host = InetAddress.getByName(IP);
                Socket socket = null;
                ObjectOutputStream oos = null;
                ObjectInputStream ois = null;
                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
                // for(int i=0; i<5;i++){
                // establish socket connection to server
                socket = new Socket(host.getHostName(), port);
                // write to socket using ObjectOutputStream
                oos = new ObjectOutputStream(socket.getOutputStream());
                while(terminar2 == false){
                // System.out.println("Sending request to Socket Server");
                System.out.print("Introduce una cadena de enteros: ");
                String sentence = inFromUser.readLine();
                if (sentence.trim().equals("0")) {
                    // ois.close();
                    oos.close();
                    socket.close();
                    terminar2 =true;
                    return;
                }
                oos.writeObject(sentence);
                // if(i==4)oos.writeObject("exit");
                // else oos.writeObject(""+i);
                // read the server response message
                ois = new ObjectInputStream(socket.getInputStream());
                String message = (String) ois.readObject();
                System.out.println(message);
            }
                // close resources
                ois.close();
                oos.close();
                Thread.sleep(100);
            
                // }
                terminar2=true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
