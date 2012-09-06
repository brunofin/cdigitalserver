package bean;

import java.io.File;

public class Foto {
	// deverá pegar as fotos do banco de dados (ou arquivo)
	private File foto;
	private int id;
	
	public Foto() {
		foto = new File("");
	}
	
	public void setFoto(File foto) {
		this.foto = foto;
	}
	
	public File getFoto() {
		return foto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}