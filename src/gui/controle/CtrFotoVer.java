package gui.controle;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import gui.modelo.FrmFotoVer;

import bean.Foto;

public class CtrFotoVer implements Controle {
	private FrmFotoVer form;
	private Foto foto;
	private Controle ctrParent;
	
	public CtrFotoVer(Controle ctrParent, Foto foto) {
		this.ctrParent = ctrParent;
		form = new FrmFotoVer();
		this.foto = foto;
		System.out.println("<CtrFotoVer> Iniciando vizualização da foto: " + foto.getLocal_foto());
		configurar();
		adicionarListeners();
	}
	
	private void configurar() {
		form.setTitle("Visualizador de Imagem");
		form.getLblCaminho().setText(foto.getLocal_foto());
		
		
	}
	
	private void adicionarListeners() {
		form.getOkButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrParent.setVisible(true);
				form.dispose();
			}
		});
	}
	
	public void setVisible(boolean b) {
		form.setVisible(b);
		if(b) {
			Image img = null;
			try {
				img = ImageIO.read(new File(foto.getLocal_foto()));
				
			} catch(IOException e) {
				System.out.println("<CtrFotoVer> Erro ao abrir imagem: " + e.getMessage());
				return;
			}
			// TODO: por que essa porra não funciona?
			Graphics g = form.getPanelImagem().getGraphics();
			if(g.drawImage(img, 0, 0, null)) {
				System.out.println("<CtrFotoVer> Desenhou.");
			} else {
				System.out.println("<CtrFotoVer> Não desenhou.");
			}
			form.getLblImagem().setIcon(new ImageIcon(img.getScaledInstance(350, 170, 2)));
		}
	}

}
