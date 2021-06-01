package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {
    // Number a client
    private int clientNo = 0;
    private Map ip_catalog = new HashMap();

    @Override
    public void start(Stage primaryStage) {
        int PORT = 8000;
        primaryStage.setTitle("DSF Server");
        TilePane tile = new TilePane();
        tile.setHgap(5);
        tile.setVgap(5);
        tile.setPrefColumns(3);
        Scene scene = new Scene(tile, 450, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

        new Thread(() -> {
            try {
                ServerSocket serSock = new ServerSocket(PORT);
                while (true) {
                    // Listen for a new connection request
                    Socket socket = serSock.accept();
                    // Increment clientNo
                    clientNo++;
                    Platform.runLater( () -> {
                        // Find the client's host name, and IP address
                        InetAddress inetAddress = socket.getInetAddress();
                        ip_catalog.put(inetAddress.getHostAddress(), inetAddress);
                        tile.getChildren().add(new Button(inetAddress.getHostName()));
/*
                        ta.appendText("Client " + clientNo + "'s host name is "
                                + inetAddress.getHostName() + "\n");
                        ta.appendText("Client " + clientNo + "'s IP Address is "
                                + inetAddress.getHostAddress() + "\n");
*/
                    });
            // Create and start a new thread for the connection

                }
            } catch(IOException ex) {
                System.err.println(ex);
            }
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
