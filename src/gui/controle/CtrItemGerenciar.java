package gui.controle;

import util.Moeda;
import java.io.File;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import util.IngredienteTableModel;
import util.Unidade;

import bean.*;

import dao.categoria.CategoriaDAO;
import dao.factory.DAOFactory;
import dao.factory.Database;
import dao.item.ItemDAO;
import dao.itemingrediente.ItemIngredienteDAO;

import gui.modelo.FrmItemGerenciar;

public class CtrItemGerenciar implements Controle {
	private FrmItemGerenciar form;
	private ItemDAO itemdao;
	private CategoriaDAO categoriadao;
	private ItemIngredienteDAO itemingredientedao;
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
		form.setTitle("Gerenciar Itens");
		
		form.getComboBoxCurrency().setModel(new DefaultComboBoxModel(Moeda.values()));
		
		form.getListFotos().setModel(new DefaultListModel<Foto>());
		form.getListFotos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// TODO: configurar tabela ingredientes
		
		DAOFactory factory = DAOFactory.getDaoFactory(Database.MYSQL);
		itemdao = factory.getItemDAO();
		categoriadao = factory.getCategoriaDAO();
		itemingredientedao = factory.getItemIngredienteDAO();
		
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
				CtrIngredienteSelecionar ctr= new CtrIngredienteSelecionar(controle, listaIngrediente);
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
				IngredienteTableModel model = (IngredienteTableModel) form.getTableIngredientes().getModel();
				for(int i = 0; i < listaIngrediente.size(); i++) {
					listaIngrediente.get(i).setQuantidade((Integer) model.getValueAt(i, 2));
				}
				
				Item item = new Item();
				
				item.setNome(form.getTxtNome().getText());
				item.setDescricao(form.getTxtDescricao().getText());
				item.setPreco(Float.parseFloat(form.getTxtPreco().getText()));
				item.setFotos(form.getListFotos().getSelectedValuesList());
				item.setCategoria((Categoria) form.getComboBoxCategoria().getSelectedItem());
				item.setIngredientes(listaIngrediente);
				
				try {
					itemdao.incluir(item);
					itemingredientedao.inserir(item);	// TODO: incluir() ou inserir() ? lol
				} catch(SQLException ex) {
					System.out.println("<Erro ao incluir novo item ao sistema: " + ex.getMessage());
				}
				form.dispose();
			}
		});
	}
	
	public void setVisible(boolean b) {
		form.setVisible(b);
		
		IngredienteTableModel model = (IngredienteTableModel) form.getTableIngredientes().getModel();
		model.fireTableDataChanged();
		
		// TODO: lista ingredientes e preço compra.
		
		StringBuffer aux = new StringBuffer();
		float total = 0;
		for(int i = 0; i < model.getRowCount(); i++) {
			aux.append(((Integer) model.getValueAt(i, 2)) + " " + ((Unidade) model.getValueAt(i, 3)) + " de " + ((String) model.getValueAt(i, 0)) + ", ");
			total += ((Integer) model.getValueAt(i, 2)) * ((Float) model.getValueAt(i, 1));
		}
		form.getTxtIngredientes().setText(aux.toString());
		form.getLblPrecoCompra().setText("BRL " + total);
	}
}
