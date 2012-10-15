package gui.modelo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.Foto;
import javax.swing.JLabel;

public class FrmFotoVer extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel panelImagem;
	private JLabel lblCaminho;
	private JButton okButton;
	
	/**
	 * Create the dialog.
	 */
	public FrmFotoVer() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblVisualizarImagem = new JLabel("Visualizar Imagem:");
			contentPanel.add(lblVisualizarImagem, BorderLayout.NORTH);
		}
		{
			lblCaminho = new JLabel("CAMINHO");
			contentPanel.add(lblCaminho, BorderLayout.SOUTH);
		}
		{
			panelImagem = new JPanel();
			contentPanel.add(panelImagem, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

	public JPanel getPanelImagem() {
		return panelImagem;
	}
	public JLabel getLblCaminho() {
		return lblCaminho;
	}
	public JButton getOkButton() {
		return okButton;
	}
}
