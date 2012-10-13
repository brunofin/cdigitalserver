package gui.controle;

import java.awt.Component;
import gui.modelo.FrmFotoVer;

import bean.Foto;

public class CtrFotoVer implements Controle {
	private FrmFotoVer form;
	private Foto foto;
	private Controle ctrParent;
	
	public CtrFotoVer(Controle ctrParent, Foto foto) {
		this.ctrParent = ctrParent;
		form = new FrmFotoVer();
		this.foto = foto;
		
		configurar();
		adicionarListeners();
	}
	
	private void configurar() {
		
	}
	
	private void adicionarListeners() {
		
	}
	
	public void setVisible(boolean b) {
		form.setVisible(b);
	}

}
