package gui.modelo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;

public class FrmItemSelecionar extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTable tabelaItens;
	private JButton okButton;
	private JButton cancelButton;
	
	public FrmItemSelecionar(){
		setBounds(100, 100, 600, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		this.setResizable(false);//Não deixa maximizar a tla
		setLocationRelativeTo(null);// abre janela no centro da tela
		
		JLabel lblSelecioneOsItens = new JLabel("Selecione os Itens da Promoção");
		lblSelecioneOsItens.setBounds(12, 22, 237, 15);
		contentPanel.add(lblSelecioneOsItens);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 57, 572, 244);
		contentPanel.add(scrollPane);
		
		tabelaItens = new JTable();
		scrollPane.setViewportView(tabelaItens);
		
		
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
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public JTable getTabelaItens(){
		return tabelaItens;
	}
	public JButton getOkButton(){
		return okButton;
	}
	public JButton getCancelButton(){
		return cancelButton;
	}
}
