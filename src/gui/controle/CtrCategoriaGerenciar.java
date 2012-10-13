package gui.controle;

import java.awt.Component;

import gui.modelo.FrmCategoriaGerenciar;

public class CtrCategoriaGerenciar implements Controle {
	private FrmCategoriaGerenciar form;
	private Controle ctrParent;
	
	public CtrCategoriaGerenciar(Controle ctrParent) {
		this.ctrParent = ctrParent;
		form = new FrmCategoriaGerenciar();
	}
	
	public void setVisible(boolean b) {
		form.setVisible(b);
	}
}
