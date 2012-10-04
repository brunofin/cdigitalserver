package gui.controle;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

import gui.modelo.FrmMain;

public class CtrMain {
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
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		main.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void adicionarListeners() {
		
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
	}

}
