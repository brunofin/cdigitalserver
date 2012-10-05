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
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private JButton btnGerenciar;
	private JList listContasAtivas;
	private JList listContasFechadas;
	private JMenuItem mntmConfigurar;
	private JMenuItem mntmIniciar;
	private JMenuItem mntmParar;
	private JMenuItem mntmSair;
	private JMenuItem mntmItem;
	private JMenuItem mntmCategoria;
	private JMenuItem mntmTipo;
	private JMenuItem mntmIngrediente;
	private JMenuItem mntmCozinheiro;
	private JTextPane lblStatus;

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
		frame.setBounds(100, 100, 706, 516);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnServidor = new JMenu("Servidor");
		menuBar.add(mnServidor);
		
		mntmConfigurar = new JMenuItem("Configurar...");
		mnServidor.add(mntmConfigurar);
		
		mntmIniciar = new JMenuItem("Iniciar");
		mnServidor.add(mntmIniciar);
		
		mntmParar = new JMenuItem("Parar");
		mnServidor.add(mntmParar);
		
		JSeparator separator = new JSeparator();
		mnServidor.add(separator);
		
		mntmSair = new JMenuItem("Sair");
		mnServidor.add(mntmSair);
		
		JMenu mnManipular = new JMenu("Manipular");
		menuBar.add(mnManipular);
		
		mntmItem = new JMenuItem("Item...");
		mnManipular.add(mntmItem);
		
		mntmCategoria = new JMenuItem("Categoria...");
		mnManipular.add(mntmCategoria);
		
		mntmTipo = new JMenuItem("Tipo...");
		mnManipular.add(mntmTipo);
		
		mntmIngrediente = new JMenuItem("Ingrediente...");
		mnManipular.add(mntmIngrediente);
		
		mntmCozinheiro = new JMenuItem("Cozinheiro...");
		mnManipular.add(mntmCozinheiro);
		
		JPanel pedidosCardapioPanel = new JPanel();
		pedidosCardapioPanel.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panel_3 = new JPanel();
		
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
		
		JLabel lblStatusDoServidor = new JLabel("Status do servidor:");
		lblStatusDoServidor.setBounds(0, 0, 155, 14);
		
		lblStatus = new JTextPane();
		lblStatus.setBounds(10, 32, 145, 198);
		
		btnGerenciar = new JButton("Gerenciar");
		btnGerenciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGerenciar.setBounds(10, 234, 145, 23);
		panel_3.setLayout(null);
		panel_3.add(lblStatusDoServidor);
		panel_3.add(lblStatus);
		panel_3.add(btnGerenciar);
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

	public JTextPane getLblStatus() {
		return lblStatus;
	}

	public void setLblStatus(JTextPane lblStatus) {
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

	public JMenuItem getMntmConfigurar() {
		return mntmConfigurar;
	}

	public void setMntmConfigurar(JMenuItem mntmConfigurar) {
		this.mntmConfigurar = mntmConfigurar;
	}

	public JMenuItem getMntmIniciar() {
		return mntmIniciar;
	}

	public void setMntmIniciar(JMenuItem mntmIniciar) {
		this.mntmIniciar = mntmIniciar;
	}

	public JMenuItem getMntmParar() {
		return mntmParar;
	}

	public void setMntmParar(JMenuItem mntmParar) {
		this.mntmParar = mntmParar;
	}

	public JMenuItem getMntmSair() {
		return mntmSair;
	}

	public void setMntmSair(JMenuItem mntmSair) {
		this.mntmSair = mntmSair;
	}

	public JMenuItem getMntmItem() {
		return mntmItem;
	}

	public void setMntmItem(JMenuItem mntmItem) {
		this.mntmItem = mntmItem;
	}

	public JMenuItem getMntmCategoria() {
		return mntmCategoria;
	}

	public void setMntmCategoria(JMenuItem mntmCategoria) {
		this.mntmCategoria = mntmCategoria;
	}

	public JMenuItem getMntmTipo() {
		return mntmTipo;
	}

	public void setMntmTipo(JMenuItem mntmTipo) {
		this.mntmTipo = mntmTipo;
	}

	public JMenuItem getMntmIngrediente() {
		return mntmIngrediente;
	}

	public void setMntmIngrediente(JMenuItem mntmIngrediente) {
		this.mntmIngrediente = mntmIngrediente;
	}

	public JMenuItem getMntmCozinheiro() {
		return mntmCozinheiro;
	}

	public void setMntmCozinheiro(JMenuItem mntmCozinheiro) {
		this.mntmCozinheiro = mntmCozinheiro;
	}
}
