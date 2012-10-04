package gui.controle;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import servidor.Configuracao;

import dao.factory.Database;

import gui.modelo.FrmConfigServidor;

public class CtrConfigServidor {
	private FrmConfigServidor form;
	
	public CtrConfigServidor(Component parent) {
		form = new FrmConfigServidor(parent);
		configurar();
		adicionarListeners();
	}
	
	public void iniciar() {
		form.setVisible(true);
	}
	
	@SuppressWarnings("unchecked")
	private void configurar() {
		form.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		form.getCbBanco().setModel(new DefaultComboBoxModel<>(Database.values()));
		form.getLblPath().setText(new File(Configuracao.getFilename()).getAbsolutePath());
		form.getCbBanco().setEditable(false);
	}
	
	private void adicionarListeners() {
		form.getBtnLimpar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				limparActionPerformed();
				
			}
		});
		
		form.getBtnCancelar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				form.dispose();
				
			}
		});
		
		form.getBtnOk().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				okActionPerformed();
				
			}
		});
	}
	
	private void okActionPerformed() {
		Configuracao cfg = new Configuracao();
		
		cfg.setCardapioPorta(Integer.parseInt(form.getTfCardapioPorta().getText()));
		cfg.setDbBanco((Database) form.getCbBanco().getSelectedItem());
		cfg.setDbIp(form.getTfBancoIP().getText());
		cfg.setDbPorta(Integer.parseInt(form.getTfBancoPorta().getText()));
		cfg.setDbUsuario(form.getTfBancoUsuario().getText());
		cfg.setDbSenha(form.getPfBancoSenha().getPassword().toString());
		
		try {
			cfg.salvar();
		} catch(IOException e) {
			JOptionPane.showMessageDialog(form, "Ocorreu um erro ao tentar salvar no arquivo. Segue a mensagem:\n\n" + e.getMessage());
		}
	}
	
	private void limparActionPerformed() {
		form.getTfCardapioPorta().setText("");
		form.getTfBancoIP().setText("");
		form.getTfBancoPorta().setText("");
		form.getTfBancoUsuario().setText("");
		form.getPfBancoSenha().setText("");
	}
}
