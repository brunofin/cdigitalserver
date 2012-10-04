package gui.controle;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import servidor.Configuracao;
import servidor.conexao.Servidor;

import gui.modelo.FrmMain;

public class CtrMain {
	FrmMain main;
	
	public CtrMain() {
		main = new FrmMain();
		configurar();
		adicionarListeners();
	}
	
	private void configurar() {
		/*try {
			// imitar apar�ncia do sistema operacional
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			e.printStackTrace();
		}*/
		
		main.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void iniciar() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		File f = new File(Configuracao.getFilename());
		if(!f.exists()) {
			int resp = JOptionPane.showConfirmDialog(main.getFrame(), "O servidor ainda n�o foi configurado.\nPressione OK para configurar ou cancele.");
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
		
	}
	
	private void configurarServidorActionPerformed() {
		CtrConfigServidor cfg = new CtrConfigServidor(main.getFrame());
		cfg.iniciar();
	}
	
	private void servidorIniciarActionPerformed() {
		new Thread(new Servidor()).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(Servidor.getServidor().isServerAlive()) {
					Calendar c = Calendar.getInstance();
					main.getLblStatus().setText(
							"Hor�rio: " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) +
							"\nIP: localhost (TODO)" +
							"\nTempo ativo: " + ((System.currentTimeMillis() - Servidor.getServidor().getAtivadoEm()))/1000 + " segundos" +
							"\nClientes conectados: " + Servidor.getServidor().getConexoes().size() +
							"\n-An�nimos: TODO" +
							"\n-Cadastrados: TODO" +
							"\nLucro total: TODO");
					long time = System.currentTimeMillis();
					while((System.currentTimeMillis() - time) <= 3000) {}
				}
			}
		}).start();
		
	}

}
