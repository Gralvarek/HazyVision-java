package java.hazyvision;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.ServerSocketConnection;
import javax.microedition.io.SocketConnection;
import java.util.Vector;


public class HazyConnect implements Runnable{
    
    SocketConnection connection;
    
    public HazyConnect(SocketConnection c) {
        connection = c;
    }
    
    public void run() {
        try {
            InputStream is = connection.openInputStream();
            byte[] b = new byte[1024];
            while(is.available() > 0) {
                int read = is.read(b);
                for(int i = 0; i < read; i++) {
                    byte reading = b[i];
                    int byteRead = reading;
                    this.parseReading(byteRead);
                }
            }
            is.close();
            connection.close();
        
        }
        catch (IOException e) {
        } 
    }
    
    private String parseReading(int byteRead) {
        return "";
    }
}
