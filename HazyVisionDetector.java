import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.ServerSocketConnection;
import javax.microedition.io.SocketConnection;
import java.util.Vector;

public class HazyVisionDetector {

  Vector byteVector = new Vector();
  int[] byteInt = new int[5];
  SocketConnection connection;
  int listenPort;
  String hazyOutput;

  public HazyVisionDetector(int lPort) {
    listenPort = lPort;
  }

  public String hazyConnect() {
    ServerSocketConnection s = null;
    hazyOutput = "";
    try {
      s = (ServerSocketConnection) Connector.open("serversocket://:" + listenport);
      SocketConnection connection = (SocketConnection) s.acceptAndOpen();
      for(i = 0; i < byteInt.length; i++) {
        hazyOutput += (String) this.hazyParse(i);
      }
    }
    catch(IOException ex) {
        System.out.println("There was an error?!?!?!?");
    }
    return hazyOutput;
  }

  private int hazyParse(int iterator) {

    int[] bStream = this.hazyRead();
    int bIterated = bStream[iterator];
    return bIterated;
  }

  private int[] hazyRead() {
    try {
        InputStream is = connection.openInputStream();
        byte[] b = new byte[1024];
        if(is.available() > 0) {
            int read = is.read(b);
            for(int i = 0; i < read; i++) {
                byte reading = b[i];
                int byteRead = reading;
                if(byteRead != 0) {
                  byteVector.add(byteRead);
                }
            }
        }
        is.close();
        connection.close();
    }
    catch (IOException e) {
    }
    return byteInt = byteVector.toArray();
  }
}
