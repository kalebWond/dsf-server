import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Server {

    public static void main(String args[]) throws RemoteException, NotBoundException, UnknownHostException, IOException {
        try {
            int PORT = 8000;
            ServerSocket serSoc = new ServerSocket(PORT);
            Socket socket = serSoc.accept();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
