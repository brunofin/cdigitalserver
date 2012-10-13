package gui.controle;

import java.awt.Component;

import gui.modelo.FrmTipoGerenciar;

public class CtrTipoGerenciar implements Controle {
	private FrmTipoGerenciar form;
	private Controle ctrParent;
	
	public CtrTipoGerenciar(Controle ctrParent) {
		this.ctrParent = ctrParent;
		form = new FrmTipoGerenciar();
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
