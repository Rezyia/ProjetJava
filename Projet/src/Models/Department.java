package Models;

import java.io.Serializable;

/**
 * Classe représentant un département
 *
 */
public class Department implements Serializable{
	
	private static final long serialVersionUID = 2688262968413929410L;
	
	private String nameDep;
	
	public Department(String name){
		nameDep = name;
	}
	
	/**
	 * Getter du nom de département
	 * @return Le nom du département
	 */
	public String getnameDep() {
		return nameDep;
	}
	
	/**
	 * Setter du nom de département
	 * @param name Le nouveau nom du département
	 */
	public void setnameDep(String name) {
		nameDep = name;
	}

	public String toString() {
		return nameDep;
	}
}
