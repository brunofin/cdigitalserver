package gui.modelo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class FrmCozinheiroListar extends JDialog{
	private final JPanel contentPanel = new JPanel();
	private JTable tabelaCozinheiros;
	private JButton btnIncluir;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton cancelButton;
	
	public FrmCozinheiroListar() {
		setTitle("Lista de Cozinheiros");
		setBounds(100, 100, 680, 530);
		getContentPane().setLayout(new BorderLayout());
		
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(56, 84, 556, 297);
		contentPanel.add(scrollPane);
		tabelaCozinheiros = new JTable();
		scrollPane.setViewportView(tabelaCozinheiros);
		
		JLabel lblListaDeCozinheiros = new JLabel("Lista de Cozinheiros:");
		lblListaDeCozinheiros.setBounds(56, 44, 167, 15);
		contentPanel.add(lblListaDeCozinheiros);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(495, 393, 117, 25);
		contentPanel.add(btnExcluir);
		
		btnEditar = new JButton("Ver / Editar");
		btnEditar.setBounds(368, 393, 117, 25);
		contentPanel.add(btnEditar);
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.setBounds(240, 393, 117, 25);
		contentPanel.add(btnIncluir);
		
		
		this.setResizable(false);//Não deixa maximizar a tla
		setLocationRelativeTo(null);// abre janela no centro da tela
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JTable getTabelaCozinheiros() {
		return tabelaCozinheiros;
	}
	public JButton getBtnIncluir() {
		return btnIncluir;
	}
	public JButton getBtnEditar() {
		return btnEditar;
	}
	public JButton getBtnExcluir() {
		return btnExcluir;
	}
	public JButton getCancelButton() {
		return cancelButton;
	}
	
}
