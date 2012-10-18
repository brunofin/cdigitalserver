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

import gui.modelo.FrmServidorConfigurar;

public class CtrServidorConfigurar implements Controle {
	private FrmServidorConfigurar form;
	private Controle ctrParent;
	
	public CtrServidorConfigurar(Controle ctrParent) {
		this.ctrParent = ctrParent;
		form = new FrmServidorConfigurar();
		configurar();
		adicionarListeners();
	}
	
	public void setVisible(boolean b) {
		form.setVisible(b);
	}
	
	private void configurar() {
		form.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		form.setTitle("Configurar servidor");
		form.getCbBanco().setModel(new DefaultComboBoxModel<Database>(Database.values()));
		form.getLblPath().setText(new File(Configuracao.getFilename()).getAbsolutePath());
		form.getCbBanco().setEditable(false);
		
		File f = new File(Configuracao.getFilename());
		if(f.exists()) {
			Configuracao cfg = new Configuracao();
			
			try {
				cfg.ler();
			} catch (IOException | ClassNotFoundException e) {
				System.out.println("<CtrServidorConfigurar> Erro ao ler configurações: " + e.getMessage());
			}
			
			form.getTfCardapioPorta().setText(cfg.getCardapioPorta() + "");
			form.getTfBancoIP().setText(cfg.getDbIp());
			form.getTfBancoPorta().setText(cfg.getDbPorta() + "");
			form.getTfBancoUsuario().setText(cfg.getDbUsuario());
			form.getPfBancoSenha().setText(cfg.getDbSenha());
			form.getCbBanco().setSelectedItem(cfg.getDbBanco());
		}
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
		cfg.setDbSenha(form.getPfBancoSenha().getText());
		
		try {
			cfg.salvar();
		} catch(IOException e) {
			JOptionPane.showMessageDialog(form, "Ocorreu um erro ao tentar salvar no arquivo. Segue a mensagem:\n\n" + e.getMessage());
		}
		
		form.dispose();
	}
	
	private void limparActionPerformed() {
		form.getTfCardapioPorta().setText("");
		form.getTfBancoIP().setText("");
		form.getTfBancoPorta().setText("");
		form.getTfBancoUsuario().setText("");
		form.getPfBancoSenha().setText("");
	}
}
