package gui.controle;

import gui.modelo.FrmClienteGerenciar;
import bean.Cliente;

public class CtrClienteGerenciar implements Controle {
	
	private FrmClienteGerenciar form;
	private Controle parent;
	private boolean editando;
	private Cliente cliente;
	
	public CtrClienteGerenciar(Controle parent) {
		this.parent = parent;
		cliente = new Cliente();
		form = new FrmClienteGerenciar();
		editando = false;
		
		configurar();
		adicionarListeners();
	}
	
	public CtrClienteGerenciar(Controle parent, Cliente cliente) {
		this.parent = parent;
		this.cliente = cliente;
		form = new FrmClienteGerenciar();
		editando = true;
		
		configurar();
		adicionarListeners();
	}
	
	private void configurar() {
		
	}
	
	private void adicionarListeners() {
		
	}

	@Override
	public void setVisible(boolean b) {
		form.setVisible(true);
	}

}
