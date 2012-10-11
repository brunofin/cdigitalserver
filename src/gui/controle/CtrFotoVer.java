package gui.controle;

import java.awt.Component;
import gui.modelo.FrmFotoVer;

import bean.Foto;

public class CtrFotoVer {
	private FrmFotoVer form;
	private Foto foto;
	
	public CtrFotoVer(Component parent, Foto foto) {
		form = new FrmFotoVer(parent);
		this.foto = foto;
		
		configurar();
		adicionarListeners();
	}
	
	private void configurar() {
		
	}
	
	private void adicionarListeners() {
		
	}
	
	public void iniciar() {
		form.setVisible(true);
	}

}
