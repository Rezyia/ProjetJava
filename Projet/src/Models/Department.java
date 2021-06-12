package Models;

import java.io.Serializable;

public class Department implements Serializable{
	
	private static final long serialVersionUID = 2688262968413929410L;
	
	private String nameDep;
	
	public Department(String Sarg){
		nameDep = Sarg;
	}
	
	public String getnameDep() {
		return nameDep;
	}
	
	public void setnameDep(String Sarg) {
		nameDep = Sarg;
	}

}
