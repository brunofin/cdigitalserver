package gui.modelo;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class FrmMain {

	private JFrame frame;
	private JList listPedidos;
	private JButton btnVerFinalizados;
	private JButton btnFinalizarselecionados;
	private JLabel lblDetalhes_1;
	private JList listDetalhes;
	private JLabel lblTotal;
	private JButton btnFinalizarPedido;
	private JList listCardapio;
	private JButton btnDetalhes;
	private JLabel lblStatus;
	private JButton btnGerenciar;
	private JList listContasAtivas;
	private JList listContasFechadas;

	/**
	 * Create the application.
	 */
	public FrmMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 625, 451);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnServidor = new JMenu("Servidor");
		menuBar.add(mnServidor);
		
		JMenuItem mntmConfigurar = new JMenuItem("Configurar...");
		mnServidor.add(mntmConfigurar);
		
		JMenuItem mntmIniciar = new JMenuItem("Iniciar");
		mnServidor.add(mntmIniciar);
		
		JMenuItem mntmParar = new JMenuItem("Parar");
		mnServidor.add(mntmParar);
		
		JSeparator separator = new JSeparator();
		mnServidor.add(separator);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mnServidor.add(mntmSair);
		
		JMenu mnManipular = new JMenu("Manipular");
		menuBar.add(mnManipular);
		
		JMenuItem mntmItem = new JMenuItem("Item...");
		mnManipular.add(mntmItem);
		
		JMenuItem mntmCategoria = new JMenuItem("Categoria...");
		mnManipular.add(mntmCategoria);
		
		JMenuItem mntmTipo = new JMenuItem("Tipo...");
		mnManipular.add(mntmTipo);
		
		JMenuItem mntmIngrediente = new JMenuItem("Ingrediente...");
		mnManipular.add(mntmIngrediente);
		
		JMenuItem mntmCozinheiro = new JMenuItem("Cozinheiro...");
		mnManipular.add(mntmCozinheiro);
		
		JPanel pedidosCardapioPanel = new JPanel();
		pedidosCardapioPanel.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panel_3 = new JPanel();
		
		JPanel statusPanel = new JPanel();
		panel_3.add(statusPanel);
		statusPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblStatusDoServidor = new JLabel("Status do servidor:");
		statusPanel.add(lblStatusDoServidor);
		
		lblStatus = new JLabel("STATUS");
		statusPanel.add(lblStatus);
		
		btnGerenciar = new JButton("Gerenciar");
		statusPanel.add(btnGerenciar);
		
		JPanel panel_4 = new JPanel();
		
		JPanel contasPanel = new JPanel();
		panel_4.add(contasPanel);
		contasPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblContasAtivas = new JLabel("Contas ativas:");
		contasPanel.add(lblContasAtivas);
		
		listContasAtivas = new JList();
		contasPanel.add(listContasAtivas);
		
		JLabel lblContasFechadas = new JLabel("Contas fechadas");
		contasPanel.add(lblContasFechadas);
		
		listContasFechadas = new JList();
		contasPanel.add(listContasFechadas);
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel detalhesPanel = new JPanel();
		panel_2.add(detalhesPanel);
		detalhesPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblDetalhes = new JLabel("Detalhes:");
		detalhesPanel.add(lblDetalhes);
		
		lblDetalhes_1 = new JLabel("DETALHES");
		detalhesPanel.add(lblDetalhes_1);
		
		listDetalhes = new JList();
		detalhesPanel.add(listDetalhes);
		
		lblTotal = new JLabel("Total:");
		detalhesPanel.add(lblTotal);
		
		btnFinalizarPedido = new JButton("Finalizar pedido");
		detalhesPanel.add(btnFinalizarPedido);
		
		JPanel cardapiosPanel = new JPanel();
		panel_2.add(cardapiosPanel);
		cardapiosPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblCardpiosConectados = new JLabel("Card\u00E1pios conectados:");
		cardapiosPanel.add(lblCardpiosConectados);
		
		listCardapio = new JList();
		cardapiosPanel.add(listCardapio);
		
		btnDetalhes = new JButton("Detalhes");
		cardapiosPanel.add(btnDetalhes);
		
		JPanel panel = new JPanel();
		
		JPanel pedidosPanel = new JPanel();
		panel.add(pedidosPanel);
		pedidosPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblPedidos = new JLabel("Pedidos:");
		pedidosPanel.add(lblPedidos);
		
		listPedidos = new JList();
		pedidosPanel.add(listPedidos);
		
		btnVerFinalizados = new JButton("Ver finalizados");
		pedidosPanel.add(btnVerFinalizados);
		
		btnFinalizarselecionados = new JButton("FinalizarSelecionados");
		pedidosPanel.add(btnFinalizarselecionados);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(pedidosCardapioPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(151))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
						.addComponent(panel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
						.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pedidosCardapioPanel, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
					.addGap(146))
		);
		frame.getContentPane().setLayout(groupLayout);
	}

	public JList getListPedidos() {
		return listPedidos;
	}

	public void setListPedidos(JList listPedidos) {
		this.listPedidos = listPedidos;
	}

	public JButton getBtnVerFinalizados() {
		return btnVerFinalizados;
	}

	public void setBtnVerFinalizados(JButton btnVerFinalizados) {
		this.btnVerFinalizados = btnVerFinalizados;
	}

	public JButton getBtnFinalizarselecionados() {
		return btnFinalizarselecionados;
	}

	public void setBtnFinalizarselecionados(JButton btnFinalizarselecionados) {
		this.btnFinalizarselecionados = btnFinalizarselecionados;
	}

	public JLabel getLblDetalhes_1() {
		return lblDetalhes_1;
	}

	public void setLblDetalhes_1(JLabel lblDetalhes_1) {
		this.lblDetalhes_1 = lblDetalhes_1;
	}

	public JList getListDetalhes() {
		return listDetalhes;
	}

	public void setListDetalhes(JList listDetalhes) {
		this.listDetalhes = listDetalhes;
	}

	public JLabel getLblTotal() {
		return lblTotal;
	}

	public void setLblTotal(JLabel lblTotal) {
		this.lblTotal = lblTotal;
	}

	public JButton getBtnFinalizarPedido() {
		return btnFinalizarPedido;
	}

	public void setBtnFinalizarPedido(JButton btnFinalizarPedido) {
		this.btnFinalizarPedido = btnFinalizarPedido;
	}

	public JList getListCardapio() {
		return listCardapio;
	}

	public void setListCardapio(JList listCardapio) {
		this.listCardapio = listCardapio;
	}

	public JButton getBtnDetalhes() {
		return btnDetalhes;
	}

	public void setBtnDetalhes(JButton btnDetalhes) {
		this.btnDetalhes = btnDetalhes;
	}

	public JLabel getLblStatus() {
		return lblStatus;
	}

	public void setLblStatus(JLabel lblStatus) {
		this.lblStatus = lblStatus;
	}

	public JButton getBtnGerenciar() {
		return btnGerenciar;
	}

	public void setBtnGerenciar(JButton btnGerenciar) {
		this.btnGerenciar = btnGerenciar;
	}

	public JList getListContasAtivas() {
		return listContasAtivas;
	}

	public void setListContasAtivas(JList listContasAtivas) {
		this.listContasAtivas = listContasAtivas;
	}

	public JList getListContasFechadas() {
		return listContasFechadas;
	}

	public void setListContasFechadas(JList listContasFechadas) {
		this.listContasFechadas = listContasFechadas;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
