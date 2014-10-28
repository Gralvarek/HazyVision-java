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
        } 
		catch(IOException ex) {
			System.out.println("Could not connect or port is already open");
        }
        // Attempts to connect
        try {
            connection = (SocketConnection) s.acceptAndOpen();
        } 
		catch(IOException e) {
        }
        // Assigns an int to the output of HazyRead
        hazyOutput = this.hazyRead();
        // Returns the assigned int
        return hazyOutput;
    }

    private int hazyRead() {
		// Sets the integer that will be returned to 0
		int byteRead = 0;
		byte[] b = new byte[1024];
        // Attempts to receive input from socket
		try {
            InputStream is = connection.openInputStream();
			// If there are bytes in the stream then
			// the byte array is assigned to an int
            if(is.available() > 0) {
                int read = is.read(b);
				// Iterates through each byte in the
				// byte array and assigns it 
                for(int i = 0; i < read; i++) {
                    byte reading = b[i];
                    byteRead = reading;
                }
            }
			// Closes both the input stream and socket
            is.close();
            connection.close();
        }
		catch (IOException e) {
            System.out.println("OOOOPSIE Error: "+e);
        }
		// Returns each assigned int
        return byteRead;
    }
}
