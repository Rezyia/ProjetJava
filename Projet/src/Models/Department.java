package Models;

import java.io.Serializable;

/**
 * Classe repr�sentant un d�partement
 *
 */
public class Department implements Serializable{
	
	private static final long serialVersionUID = 2688262968413929410L;
	
	private String nameDep;
	
	public Department(String name){
		nameDep = name;
	}
	
	/**
	 * Getter du nom de d�partement
	 * @return Le nom du d�partement
	 */
	public String getnameDep() {
		return nameDep;
	}
	
	/**
	 * Setter du nom de d�partement
	 * @param name Le nouveau nom du d�partement
	 */
	public void setnameDep(String name) {
		nameDep = name;
	}

	public String toString() {
		return nameDep;
	}
}
