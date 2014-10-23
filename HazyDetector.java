package java.hazyvision;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.ServerSocketConnection;
import javax.microedition.io.SocketConnection;
import java.util.Vector;
     

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author FRC
 */
public class HazyDetector implements Runnable {
    
    private final int listenport;
    Vector connections;
    /**IOException ex = new IOException();**/
    
    public HazyDetector(int port) {
        listenport = port;
        connections = new Vector();
    }
    
    public void run() {
        ServerSocketConnection s = null;
        try {
            s = (ServerSocketConnection) Connector.open("serversocket://:" + listenport);
            
            while(true) {
                SocketConnection connection = (SocketConnection) s.acceptAndOpen();
                Thread connect = new Thread(new HazyConnect(connection));
                connect.start();
                connections.addElement(connection);
            }
        }
        catch(IOException ex) {
            System.out.println("There was an error.");
        }
   }
}