package gui.modelo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import bean.Ingrediente;

public class FrmIngredienteEditar extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JList<Ingrediente> listIngredientes;
	private JButton cancelButton;
	private JButton okButton;
	/**
	 * Create the dialog.
	 */
	public FrmIngredienteEditar() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblSelecioneOsIngredientes = new JLabel("Selecione os ingredientes da lista:");
		lblSelecioneOsIngredientes.setBounds(12, 12, 424, 15);
		contentPanel.add(lblSelecioneOsIngredientes);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 223, 424, -178);
		contentPanel.add(scrollPane);
		
		listIngredientes = new JList<Ingrediente>();
		scrollPane.setViewportView(listIngredientes);
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
	public JList<Ingrediente> getListIngredientes() {
		return listIngredientes;
	}
	public JButton getCancelButton() {
		return cancelButton;
	}
	public JButton getOkButton() {
		return okButton;
	}
}
