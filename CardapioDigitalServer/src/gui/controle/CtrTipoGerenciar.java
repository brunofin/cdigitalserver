package gui.controle;

import java.awt.Component;

import gui.modelo.FrmTipoGerenciar;

public class CtrTipoGerenciar {
	private FrmTipoGerenciar form;
	
	public CtrTipoGerenciar(Component parent) {
		form = new FrmTipoGerenciar(parent);
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
