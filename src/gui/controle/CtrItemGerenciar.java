package gui.controle;


import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageFilter;
import java.io.File;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import util.IngredienteTableModel;
import util.IngredienteTableModel.UNIDADE;

import bean.*;

import dao.categoria.CategoriaDAO;
import dao.factory.DAOFactory;
import dao.factory.Database;
import dao.foto.FotoDAO;
import dao.ingrediente.IngredienteDAO;
import dao.item.ItemDAO;
import dao.tipo.TipoDAO;

import gui.modelo.FrmItemGerenciar;

public class CtrItemGerenciar implements Controle {
	private FrmItemGerenciar form;
	private ItemDAO itemdao;
	private CategoriaDAO categoriadao;
	private TipoDAO tipodao;
	private FotoDAO fotodao;
	private IngredienteDAO ingredientedao;
	private List<Ingrediente> listaIngrediente;
	private Controle ctrParent;
	
	public CtrItemGerenciar(Controle ctrParent) {
		this.ctrParent = ctrParent;
		form = new FrmItemGerenciar();
		configurar();
		adicionarListeners();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void configurar() {
		form.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		form.getComboBoxCurrency().setModel(new DefaultComboBoxModel(new String[] {
				"BRL", "GBP", "EUR", "USD", "PLN" }));
		
		form.getListFotos().setModel(new DefaultListModel<Foto>());
		form.getListFotos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// TODO: configurar tabela ingredientes
		
		DAOFactory factory = DAOFactory.getDaoFactory(Database.MYSQL);
		itemdao = factory.getItemDAO();
		categoriadao = factory.getCategoriaDAO();
		tipodao = factory.getTipoDAO();
		fotodao = factory.getFotoDAO();
		ingredientedao = factory.getIngredienteDAO();
		
		// popular a combobox de categorias
		List<Categoria> listaCategoria = null;
		try {
			listaCategoria = categoriadao.listar();
		} catch (SQLException e) {
			System.out.println("<CtrItemGerenciar> Erro ao listar categorias: " + e.getMessage());
		}
		for(Categoria item : listaCategoria) {
			form.getComboBoxCategoria().addItem(item);
		}
		
		listaIngrediente = new LinkedList<Ingrediente>();
		IngredienteTableModel model = new IngredienteTableModel(listaIngrediente);
		form.getTableIngredientes().setModel(model);
		
	}
	
	private void adicionarListeners() {
		final Controle controle = this;
		// Gerenciar Categorias
		form.getBtnCategoriaGerenciar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CtrCategoriaGerenciar ctr = new CtrCategoriaGerenciar(controle);
				ctr.setVisible(true);
				form.setVisible(false);
			}
		});
		
		// Ver fotos
		form.getBtnFotoVer().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CtrFotoVer ctr= new CtrFotoVer(controle, form.getListFotos().getSelectedValue());
				ctr.setVisible(true);
				form.setVisible(false);
				
			}
		});
		
		// Adicionar fotos (na lista, não no bd ainda)
		form.getBtnFotoAdicionar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fc.addChoosableFileFilter(new FileFilter() {
					private String jpeg = "jpeg";
					private String jpg = "jpg";
					private String gif = "gif";
					private String tiff = "tiff";
					private String tif = "tif";
					private String png = "png";
					
					private String getExtension(File f) {
				        String ext = null;
				        String s = f.getName();
				        int i = s.lastIndexOf('.');

				        if (i > 0 &&  i < s.length() - 1) {
				            ext = s.substring(i+1).toLowerCase();
				        }
				        return ext;
				    }
				    
					public boolean accept(File f) {
				        if (f.isDirectory()) {
				            return true;
				        }

				        String extension = getExtension(f);
				        if (extension != null) {
				            if (extension.equals(tiff) ||
				                extension.equals(tif) ||
				                extension.equals(gif) ||
				                extension.equals(jpeg) ||
				                extension.equals(jpg) ||
				                extension.equals(png)) {
				                    return true;
				            } else {
				                return false;
				            }
				        }
				        return false;
				    }

				    public String getDescription() {
				        return "Somente imagens";
				    }
				});
				
				if(fc.showOpenDialog(form.getContentPane()) == JFileChooser.APPROVE_OPTION) {
					Foto f = new Foto(fc.getSelectedFile().getAbsolutePath());
					
					
					DefaultListModel<Foto> model = (DefaultListModel<Foto>) form.getListFotos().getModel();
					model.addElement(f);
				}
			}
		});
		
		// Remover fotos (da lista, não do bd)
		form.getBtnFotoRemover().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultListModel<Foto> model = (DefaultListModel<Foto>) form.getListFotos().getModel();
				model.remove(form.getListFotos().getSelectedIndex());
			}
		});
		
		// Editar ingredientes
		form.getBtnIngredienteEditar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CtrIngredienteEditar ctr= new CtrIngredienteEditar(controle, listaIngrediente);
				ctr.setVisible(true);
				form.setVisible(false);
			}
		});
		
		// Botão limpar
		form.getLimparButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				form.getTxtNome().setText("");
				form.getTxtDescricao().setText("");
				form.getTxtPreco().setText("");
				((DefaultListModel<Foto>) form.getListFotos().getModel()).clear();
				
			}
		});
		
		// Botão cancelar
		form.getCancelButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				form.dispose();
			}
		});
		
		// Botão OK
		form.getOkButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Item item = new Item();
				
				item.setNome(form.getTxtNome().getText());
				item.setDescricao(form.getTxtDescricao().getText());
				item.setPreco(Float.parseFloat(form.getTxtPreco().getText()));
				item.setFotos(form.getListFotos().getSelectedValuesList());
				item.setCategoria((Categoria) form.getComboBoxCategoria().getSelectedItem());
				item.setIngredientes(listaIngrediente);
				
				try {
					itemdao.incluir(item);
				} catch(SQLException ex) {
					System.out.println("<Erro ao incluir novo item ao sistema: " + ex.getMessage());
				}
				form.dispose();
			}
		});
	}
	
	public void setVisible(boolean b) {
		form.setVisible(b);
		
		listaIngrediente = new LinkedList<Ingrediente>();
		IngredienteTableModel model = new IngredienteTableModel(listaIngrediente);
		form.getTableIngredientes().setModel(model);
		
		// TODO: lista ingredientes e preço compra.
		
		StringBuffer aux = new StringBuffer();
		float total = 0;
		for(int i = 0; i < model.getRowCount(); i++) {
			aux.append(((Float) model.getValueAt(i, 2)) + " " + ((UNIDADE) model.getValueAt(i, 3)) + " de " + model.getValueAt(i, 0) + ", ");
			total += ((Float) model.getValueAt(i, 2)) * ((Float) model.getValueAt(i, 1));
		}
		form.getTxtIngredientes().setText(aux.toString());
		form.getLblPrecoCompra().setText("BRL " + total);
	}
}
