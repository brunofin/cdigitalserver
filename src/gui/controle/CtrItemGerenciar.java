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
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import bean.*;

import dao.categoria.CategoriaDAO;
import dao.factory.DAOFactory;
import dao.factory.Database;
import dao.foto.FotoDAO;
import dao.ingrediente.IngredienteDAO;
import dao.item.ItemDAO;
import dao.tipo.TipoDAO;

import gui.modelo.FrmItemGerenciar;

public class CtrItemGerenciar {
	private FrmItemGerenciar form;
	private ItemDAO itemdao;
	private CategoriaDAO categoriadao;
	private TipoDAO tipodao;
	private FotoDAO fotodao;
	private IngredienteDAO ingredientedao;
	
	public CtrItemGerenciar(Component parent) {
		form = new FrmItemGerenciar(parent);
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
		
		form.getListIngredientes().setModel(new DefaultListModel<Ingrediente>());
		form.getListIngredientes().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		DAOFactory factory = DAOFactory.getDaoFactory(Database.MYSQL);
		itemdao = factory.getItemDAO();
		categoriadao = factory.getCategoriaDAO();
		tipodao = factory.getTipoDAO();
		fotodao = factory.getFotoDAO();
		ingredientedao = factory.getIngredienteDAO();
		
		/* TODO: popular:
		 * 
		 * combobox categoria
		 * combobox tipo
		 * list fotos
		 * list ingredientes
		 */
		
		popularListaIngredientes();
	}
	
	private void adicionarListeners() {
		// Gerenciar Categorias
		form.getBtnCategoriaGerenciar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CtrCategoriaGerenciar ctr = new CtrCategoriaGerenciar(form.getContentPane());
				ctr.iniciar();
				form.setVisible(false);
			}
		});
		
		// Gerenciar Tipos
		form.getBtnTipoGerenciar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CtrTipoGerenciar ctr = new CtrTipoGerenciar(form.getContentPane());
				ctr.iniciar();
				form.setVisible(false);
			}
		});
		
		// Ver fotos
		form.getBtnFotoVer().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CtrFotoVer ctr= new CtrFotoVer(form.getContentPane(), form.getListFotos().getSelectedValue());
				ctr.iniciar();
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
		
		// Gerenciar ingredientes
		form.getBtnIngredienteGerenciar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CtrIngredienteGerenciar ctr= new CtrIngredienteGerenciar(form.getContentPane());
				ctr.iniciar();
				form.setVisible(false);
			}
		});
		
		// Botão limpar
		form.getLimparButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				form.getTxtNome().setText("");
				form.getTxtDescricao().setText("");
				form.getTextFieldCurrency().setText("");
				((DefaultListModel<Foto>) form.getListFotos().getModel()).clear();
				popularListaIngredientes();
			}
		});
	}
	
	private void popularListaIngredientes() {
		List<Ingrediente> ingredientes = null;
		try {
			ingredientes = ingredientedao.listar();
		} catch(SQLException e) {
			System.out.println("<CtrItemGereciar> Erro ao recuperar lista de ingredientes do BD: " + e.getMessage());
			return;
		}
		
		DefaultListModel<Ingrediente> model = (DefaultListModel<Ingrediente>) form.getListIngredientes().getModel();
		model.clear();
		
		for(Ingrediente i : ingredientes) {
			model.addElement(i);
		}
		
		form.getTxtIngredientes().setText("");
		form.getLblPrecoCompra().setText("BRL 0.00");
		
	}
	
	public void iniciar() {
		form.setVisible(true);
	}
}
