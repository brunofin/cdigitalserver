package gui.controle;

import java.awt.Component;

import gui.modelo.FrmIngredienteGerenciar;

public class CtrIngredienteGerenciar implements Controle {
	private FrmIngredienteGerenciar form;
	private Controle ctrParent;
	
	public CtrIngredienteGerenciar(Controle ctrParent) {
		form = new FrmIngredienteGerenciar();
		this.ctrParent = ctrParent;
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
