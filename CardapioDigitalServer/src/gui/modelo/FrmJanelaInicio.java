package gui.modelo;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class FrmJanelaInicio extends JFrame {
	JMenuBar menuBar;
	JMenu menuOperacoes;
	public JMenuItem menuManipularItem;
	public JMenuItem menuManipularCategoria;
	public JMenuItem menuItemPratos;
	public JMenuItem menuItemBebidas;
	public JMenuItem menuItemSobremesas;
	public JMenuItem menuItemEntradas;
	public JMenuItem menuManipularIngrediente;
	public JMenuItem menuManipularCozinheiro;
	public JMenuItem menuItemSair;
	
	public FrmJanelaInicio() {
		inicializar();
	}
	
	private void inicializar() {
		instanciar();
		posicionar();
        agrupar();
        configurar();
	}
	
	private void instanciar() {
		menuBar = new JMenuBar();
		menuOperacoes = new JMenu("Operações");
		menuManipularItem = new JMenu("Manipular Itens");
		menuItemPratos = new JMenuItem("Pratos");
		menuItemBebidas = new JMenuItem("Bebidas");
		menuItemSobremesas = new JMenuItem("Sobremesas");
		menuItemEntradas = new JMenuItem("Entradas");
		menuManipularCategoria = new JMenuItem("Manipular Categorias");
		menuManipularIngrediente = new JMenuItem("Manipular Ingredientes");
		menuManipularCozinheiro = new JMenuItem("Manipular Cozinheiros");
		menuItemSair = new JMenuItem("Sair");
	}
	
	private void posicionar() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);
	}
	
	private void agrupar() {
		setJMenuBar(menuBar);
		menuBar.add(menuOperacoes);
		menuManipularItem.add(menuItemPratos);
		menuManipularItem.add(menuItemBebidas);
		menuManipularItem.add(menuItemSobremesas);
		menuManipularItem.add(menuItemEntradas);
		menuOperacoes.add(menuManipularItem);
		menuOperacoes.add(menuManipularCategoria);
		menuOperacoes.add(menuManipularIngrediente);
		menuOperacoes.add(menuManipularCozinheiro);
		menuOperacoes.add(menuItemSair);
	}
	
	private void configurar() {
		setTitle("Cardápio Digital Server");
		menuOperacoes.setText("Operações");
		menuManipularItem.setText("Manipular Itens");
		menuItemPratos.setText("Pratos");
		menuItemBebidas.setText("Bebidas");
		menuItemSobremesas.setText("Sobremesas");
		menuItemEntradas.setText("Entradas");
		menuManipularCategoria.setText("Manipular Categorias");
		menuManipularIngrediente.setText("Manipular Ingredientes");
		menuManipularCozinheiro.setText("Manipular Cozinheiros");
		menuItemSair.setText("Sair");
	}
}
