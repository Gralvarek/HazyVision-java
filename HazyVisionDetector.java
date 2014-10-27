package java.hazyvision;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.ServerSocketConnection;
import javax.microedition.io.SocketConnection;

public class HazyVisionDetector {

    private int[] byteInt = new int[5];
    private int listenPort;
    public int hazyOutput;
    private ServerSocketConnection s;
    private SocketConnection connection;

    public HazyVisionDetector(int lPort) {
        listenPort = lPort;
        s = null;
    }

    public int hazyConnect() {
        // Attempt to open port
        try {
            s = (ServerSocketConnection) Connector.open("serversocket://:" + listenPort);
            System.out.println("Let's connect!");
        } catch(IOException ex) {
        }
        // Connect
        try {
            connection = (SocketConnection) s.acceptAndOpen();
        } catch(IOException e) {
        }
        
        // Everthing else
        hazyOutput = this.hazyRead();
        
        return hazyOutput;
    }

    private int hazyRead() {
        int byteRead = 0;
        try {
            InputStream is = connection.openInputStream();
            byte[] b = new byte[1024];
            if(is.available() > 0) {
                int read = is.read(b);
                for(int i = 0; i < read; i++) {
                    byte reading = b[i];
                    byteRead = reading;
                }
            }
            is.close();
            connection.close();
        } catch (IOException e) {
            System.out.println("OOOOPSIE Error:"+e);
        }
        return byteRead;
    }
}
