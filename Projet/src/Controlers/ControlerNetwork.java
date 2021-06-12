package Controlers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ControlerNetwork implements Serializable {

	private static final long serialVersionUID = 7514011770100233285L;
	
	protected String address;
	protected int port;
	
	protected Socket s = null;
	protected InetSocketAddress isA = null;
	
	public ControlerNetwork() {
		address = "localhost";
		port = 8085;
	}
	
	public String getAddress() {
		return address;
	}
	
	public int getPort() {
		return port;
	}
	
	public void setAddress(String str) {
		address = str;
	}
	
	public void setPort(int i) {
		port = i;
	}
	
	
	// Serialization
	
	private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException{
		address = aInputStream.readUTF();
		port = aInputStream.readInt();
	}
	
    private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
    	aOutputStream.writeUTF(address);
    	aOutputStream.writeInt(port);
    }

	
}
