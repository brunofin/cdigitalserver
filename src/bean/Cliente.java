package bean;

import java.io.Serializable;

public class Cliente extends Pessoa  implements Serializable {

	public Cliente() {
		
	}
	
	public String toString() {
		return getNome() + " " + getSobrenome();
	}

}
