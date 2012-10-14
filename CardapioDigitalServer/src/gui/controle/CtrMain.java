package gui.controle;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import servidor.Configuracao;
import servidor.conexao.Servidor;

import gui.modelo.FrmMain;

public class CtrMain implements Controle {
	FrmMain main;
	
	public CtrMain() {
		main = new FrmMain();
		configurar();
		adicionarListeners();
	}
	
	private void configurar() {
		try {
			// imitar aparência do sistema operacional
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(main.getFrame());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		main.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		main.getMntmParar().setEnabled(false);
	}
	
	public void iniciar() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		File f = new File(Configuracao.getFilename());
		if(!f.exists()) {
			int resp = JOptionPane.showConfirmDialog(main.getFrame(), "O servidor ainda não foi configurado.\nPressione OK para configurar ou cancele.");
			if(resp == JOptionPane.OK_OPTION) {
				configurarServidorActionPerformed();
			}
		}
	}
	
	private void adicionarListeners() {
		// servidor -> configurar...
		main.getMntmConfigurar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				configurarServidorActionPerformed();
			}
		});
		
		// servidor -> iniciar
		main.getMntmIniciar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				servidorIniciarActionPerformed();
			}
		});
		
		//servidor -> parar
		main.getMntmParar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				servidorPararActionPerformed();
			}
		});
		
		//servidor -> sair
		main.getMntmSair().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				servidorSairActionPerformed();
			}
		});
		
		//manipular -> item
		main.getMntmItem().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manipularItemActionPerformed();
			}
		});
		
		main.getMntmCozinheiro().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				manipularCozinheiroActionPerformed();
				
			}
		});
	}
	
	private void manipularCozinheiroActionPerformed() {
		CtrCozinheiroGerenciar ctr = new CtrCozinheiroGerenciar(this);
		ctr.setVisible(true);
	}
	private void manipularItemActionPerformed() {
		CtrItemGerenciar ctr = new CtrItemGerenciar(this);
		ctr.setVisible(true);
	}
	
	private void configurarServidorActionPerformed() {
		CtrServidorConfigurar cfg = new CtrServidorConfigurar(this);
		cfg.setVisible(true);
	}
	
	private void servidorIniciarActionPerformed() {
		main.getMntmParar().setEnabled(true);
		main.getMntmIniciar().setEnabled(false);
		
		new Thread(new Servidor()).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Configuracao cfg = new Configuracao();
				try {
					cfg.ler();
				} catch(Exception e) {
					System.out.println("<CtrMain> Exceção ao ler as configurações do arquivo: " + e.getMessage());
				}
				
				while(Servidor.getServidor().isServerAlive()) {
					Calendar c = Calendar.getInstance();
					main.getLblStatus().setText(
							"Horário: " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) +
							"\nIP: " + cfg.getDbIp() + ":" + cfg.getDbPorta() +
							"\nTempo ativo: " + ((System.currentTimeMillis() - Servidor.getServidor().getAtivadoEm()))/1000 + " segundos" +
							"\nClientes conectados: " + Servidor.getServidor().getConexoes().size() +
							"\n-Anônimos: TODO" +
							"\n-Cadastrados: TODO" +
							"\nLucro total: TODO");
					long time = System.currentTimeMillis();
					while((System.currentTimeMillis() - time) <= 3000) {} //pior jeito possível de fazer uma thread parar temporariamente
				}
			}
		}).start();
		
	}
	
	private void servidorPararActionPerformed() {
		main.getMntmParar().setEnabled(false);
		main.getMntmIniciar().setEnabled(true);
		
		if(Servidor.getServidor() == null) return;
		Servidor.getServidor().finalizarServidor();
	}
	
	private void servidorSairActionPerformed() {
		servidorPararActionPerformed();
		System.exit(0);
	}
	
	public void setVisible(boolean b) {
		main.getFrame().setVisible(b);
	}

}
