package gui.controle;

import gui.modelo.FrmJanelaInicio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public class CtrJanelaInicio {
	FrmJanelaInicio janelaInicio;
	
	public CtrJanelaInicio() {
		janelaInicio = new FrmJanelaInicio();
		janelaInicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janelaInicio.menuItemSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                sairEventoClick();
            }
        });
	}
	
	public void executar() {
		janelaInicio.setVisible(true);
	}
	

	public void sairEventoClick() {
		System.exit(0);
	}
}
