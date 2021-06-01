package Controlers;

import java.net.InetSocketAddress;
import java.net.Socket;

public class ControlerNetwork {
	
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
	
}
