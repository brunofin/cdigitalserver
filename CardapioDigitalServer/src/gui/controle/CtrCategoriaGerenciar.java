package gui.controle;

import java.awt.Component;

import gui.modelo.FrmCategoriaGerenciar;

public class CtrCategoriaGerenciar {
	private FrmCategoriaGerenciar form;
	
	public CtrCategoriaGerenciar(Component parent) {
		form = new FrmCategoriaGerenciar(parent);
	}
	
	public void iniciar() {
		form.setVisible(true);
	}
}
