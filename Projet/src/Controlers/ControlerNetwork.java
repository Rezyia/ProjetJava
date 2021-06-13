package Controlers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Controleur des options de réseau
 *
 */
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
	
	/**
	 * Getter de l'adresse
	 * @return son adresse
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Getter du port
	 * @return son port
	 */
	public int getPort() {
		return port;
	}
	
	/**
	 * Setter de l'adresse
	 * @param adr La nouvelle adresse
	 */
	public void setAddress(String adr) {
		address = adr;
	}
	
	/**
	 * Setter du port
	 * @param p Le nouveau port
	 */
	public void setPort(int p) {
		port = p;
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
