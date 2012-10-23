package gui.controle;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

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
		main.getFrame().setTitle("Cardápio Digital :: Servidor - Não iniciado");
		
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
		} else {
			Configuracao cfg = new Configuracao();
			try {
				cfg.ler();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		
		//manipular -> cozinheiro
		main.getMntmCozinheiro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manipularCozinheiroActionPerformed();
			}
		});
		
		//manipular -> categoria
		main.getMntmCategoria().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manipularCategoriaActionPerformed();
			}
		});
		
		//manipular -> tipo
		main.getMntmTipo().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manipularTipoActionPerformed();
			}
		});
		
		//manipular -> promocao
		main.getMntmPromocao().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				manipularPromocaoActionPerformed();
			}
		});
		
		//manipular -> ingrediente
		main.getMntmIngrediente().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manipularIngredienteActionPerformed();
			}
		});
		
		//manipular -> cliente
		main.getMntmCliente().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manipularClienteActionPerformed();
			}
		});
	}
	
	private void manipularClienteActionPerformed() {
		CtrClienteListar ctr = new CtrClienteListar(this);
		ctr.setVisible(true);
	}
	
	private void manipularIngredienteActionPerformed() {
		CtrIngredienteGerenciar ctr = new CtrIngredienteGerenciar(this);
		ctr.setVisible(true);
	}
	
	private void manipularPromocaoActionPerformed(){
		CtrPromocaoListar ctr = new CtrPromocaoListar(this);
		ctr.setVisible(true);
	}
	
	private void manipularTipoActionPerformed() {
		CtrTipoGerenciar ctr = new CtrTipoGerenciar(this);
		ctr.setVisible(true);
	}
	
	private void manipularCategoriaActionPerformed() {
		CtrCategoriaGerenciar ctr = new CtrCategoriaGerenciar(this);
		ctr.setVisible(true);
	}

	private void manipularCozinheiroActionPerformed() {
		CtrCozinheiroListar ctr = new CtrCozinheiroListar(this);
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
	
	private void servidorPararActionPerformed() {
		main.getMntmParar().setEnabled(false);
		main.getMntmIniciar().setEnabled(true);
		
		main.getFrame().setTitle("Cardápio Digital :: Servidor - Parado");
		
		if(Servidor.getServidor() == null) return;
		Servidor.getServidor().finalizarServidor();
	}
	
	private void servidorSairActionPerformed() {
		servidorPararActionPerformed();
		System.exit(0);
	}
	
	private void servidorIniciarActionPerformed() {
		main.getMntmParar().setEnabled(true);
		main.getMntmIniciar().setEnabled(false);
		
		main.getFrame().setTitle("Cardápio Digital :: Servidor - Rodando");
		
		new Thread(new Servidor()).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				while(Servidor.getServidor().isServerAlive()) {
					Calendar c = Calendar.getInstance();
					main.getLblStatus().setText(
							"Horário: " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) +
							"\nBanco selecionado: " + Configuracao.DB_SELECIONADO +
							"\nIP: " + Configuracao.DB_ENDERECO + ":" + Configuracao.DB_PORTA +
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
	
	public void setVisible(boolean b) {
		// esta tela nunca ficará escondida
		main.getFrame().setVisible(true);
	}

}
