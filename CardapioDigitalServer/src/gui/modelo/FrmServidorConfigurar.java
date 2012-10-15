package gui.modelo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;

import dao.factory.Database;

public class FrmServidorConfigurar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfCardapioPorta;
	private JTextField tfBancoIP;
	private JTextField tfBancoPorta;
	private JTextField tfBancoUsuario;
	private JComboBox<Database> cbBanco;
	private JPasswordField pfBancoSenha;
	private JButton btnOk;
	private JButton btnCancelar;
	private JButton btnLimpar;
	private JLabel lblPath;

	/**
	 * Create the dialog.
	 */
	public FrmServidorConfigurar() {
		setBounds(100, 100, 379, 379);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(1, 1, 0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JLabel lblCardpio = new JLabel("Card\u00E1pio");
			lblCardpio.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblCardpio.setBounds(10, 11, 82, 14);
			panel.add(lblCardpio);
			
			JLabel lblPorta = new JLabel("Porta:");
			lblPorta.setBounds(20, 36, 46, 14);
			panel.add(lblPorta);
			
			tfCardapioPorta = new JTextField();
			tfCardapioPorta.setToolTipText("Porta");
			tfCardapioPorta.setBounds(222, 33, 112, 20);
			panel.add(tfCardapioPorta);
			tfCardapioPorta.setColumns(10);
			
			JLabel lblBancoDeDados = new JLabel("Banco de Dados");
			lblBancoDeDados.setBounds(10, 64, 207, 14);
			panel.add(lblBancoDeDados);
			lblBancoDeDados.setFont(new Font("Tahoma", Font.BOLD, 11));
			
			JLabel lblBanco = new JLabel("Banco:");
			lblBanco.setBounds(20, 89, 46, 14);
			panel.add(lblBanco);
			
			JLabel lblEndereoIp = new JLabel("Endere\u00E7o IP:");
			lblEndereoIp.setBounds(20, 114, 98, 14);
			panel.add(lblEndereoIp);
			
			JLabel lblPorta_1 = new JLabel("Porta:");
			lblPorta_1.setBounds(20, 139, 46, 14);
			panel.add(lblPorta_1);
			
			JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
			lblUsurio.setBounds(20, 164, 46, 14);
			panel.add(lblUsurio);
			
			JLabel lblSenha = new JLabel("Senha:");
			lblSenha.setBounds(20, 189, 46, 14);
			panel.add(lblSenha);
			
			cbBanco = new JComboBox<Database>();
			cbBanco.setBounds(222, 86, 112, 20);
			panel.add(cbBanco);
			
			tfBancoIP = new JTextField();
			tfBancoIP.setToolTipText("Endere\u00E7o IP");
			tfBancoIP.setBounds(222, 111, 112, 20);
			panel.add(tfBancoIP);
			tfBancoIP.setColumns(10);
			
			tfBancoPorta = new JTextField();
			tfBancoPorta.setToolTipText("Porta");
			tfBancoPorta.setBounds(222, 136, 112, 20);
			panel.add(tfBancoPorta);
			tfBancoPorta.setColumns(10);
			
			tfBancoUsuario = new JTextField();
			tfBancoUsuario.setToolTipText("Usu\u00E1rio");
			tfBancoUsuario.setBounds(222, 161, 112, 20);
			panel.add(tfBancoUsuario);
			tfBancoUsuario.setColumns(10);
			
			pfBancoSenha = new JPasswordField();
			pfBancoSenha.setToolTipText("Senha");
			pfBancoSenha.setBounds(222, 186, 112, 20);
			panel.add(pfBancoSenha);
			
			JLabel lblAsConfiguraesSero = new JLabel("As configura\u00E7\u00F5es ser\u00E3o salvas em:");
			lblAsConfiguraesSero.setBounds(10, 245, 324, 14);
			panel.add(lblAsConfiguraesSero);
			
			lblPath = new JLabel("PATH");
			lblPath.setBounds(10, 262, 663, 14);
			panel.add(lblPath);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			btnLimpar = new JButton("Limpar");
			buttonPane.add(btnLimpar);
			{
				btnOk = new JButton("OK");
				btnOk.setActionCommand("OK");
				buttonPane.add(btnOk);
				getRootPane().setDefaultButton(btnOk);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}

	public JTextField getTfCardapioPorta() {
		return tfCardapioPorta;
	}

	public JTextField getTfBancoIP() {
		return tfBancoIP;
	}

	public JTextField getTfBancoPorta() {
		return tfBancoPorta;
	}

	public JTextField getTfBancoUsuario() {
		return tfBancoUsuario;
	}

	public JComboBox<Database> getCbBanco() {
		return cbBanco;
	}

	public JPasswordField getPfBancoSenha() {
		return pfBancoSenha;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public JButton getBtnOk() {
		return btnOk;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JButton getBtnLimpar() {
		return btnLimpar;
	}

	public JLabel getLblPath() {
		return lblPath;
	}
}
