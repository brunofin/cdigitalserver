package gui.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.TableColumn;

import util.ItemTableModel;

import bean.Foto;
import bean.Item;
import bean.Promocao;

import dao.factory.DAOFactory;
import dao.factory.Database;
import dao.foto.MySqlFotoDAO;
import dao.item.ItemDAO;
import dao.promocao.MySqlPromocaoDAO;

import gui.modelo.FrmPromocaoCadastrar;


public class CtrPromocaoCadastrar implements Controle {
	private FrmPromocaoCadastrar form;
	private Controle ctrParent;
	private Promocao promocao;
	private List <Item> todosOsItens;
	private ItemDAO itemDAO;
	
	public CtrPromocaoCadastrar(Controle ctrParent){
		this.ctrParent = ctrParent;
		form = new FrmPromocaoCadastrar();
		configurar();
		adicionarListeners();
	}
	
	private void configurar(){
		form.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		DAOFactory factory = DAOFactory.getDaoFactory(Database.MYSQL);
		itemDAO = factory.getItemDAO();
		try {
			todosOsItens = itemDAO.listar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		promocao = new Promocao();
	}
	
	private void adicionarListeners(){
		final Controle controle = this;
		// Botão cancelar
		form.getCancelButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				form.dispose();
			}
		});
		// botão limpar
		form.getLimparButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {//OK
				promocao = new Promocao();
				form.getTxtFoto().setText("");
				form.getTxtNome().setText("");
				form.getTxtDataInico().setText("");
				form.getTxtValidade().setText("");
				form.getTxtAreaDescricao().setText("");
				ItemTableModel tableModel =(ItemTableModel) form.getTabelaItens().getModel();  
				tableModel.setNumRows(0);
				form.getTabelaItens().repaint();
			}
		});
		//botao adicionar itens
		form.getBtnAdicionarItem().addActionListener(new ActionListener(){//OK
			public void actionPerformed(ActionEvent e) {
				if(todosOsItens == null || todosOsItens.isEmpty()){
					JOptionPane.showMessageDialog
					(null, "Não existe nenhum item cadastrado no sistema, " +
							"favor cadastrar itens", "Nenhum Item Cadastrado", 
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				CtrItemSelecionar ctr = new CtrItemSelecionar(controle, todosOsItens, promocao.getItens());
				ctr.setVisible(true);
				form.setVisible(false);
			}
		});	
		//botao OK
		form.getOkButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//validarCampos
				//List <String> erros = validarCampos();
				promocao = preencherPromocao();
				//inserirBD
			}
		});
		
		//botao Excluir Selecionados
		form.getBtnExcluirSelecionados().addActionListener(new ActionListener (){//OK

			@Override
			public void actionPerformed(ActionEvent e) {
				int itensSelecionados [] = form.getTabelaItens().getSelectedRows();
				if(itensSelecionados.length > 0 && !promocao.getItens().isEmpty()) {
					for (int i = 0; i < itensSelecionados.length; i++) {
						promocao.getItens().remove(itensSelecionados[i]);
						
					}
					
					form.getTabelaItens().repaint();
					
				}else {
					JOptionPane.showMessageDialog
					(null, "Nenhum item selecionado para ser excluído!", 
							"Nenhum item selecionado", JOptionPane.WARNING_MESSAGE);
				}
				
			}
			
		});
		// Ver fotos
		form.getBtnVerFoto().addActionListener(new ActionListener() {//TODO testar
			@Override
			public void actionPerformed(ActionEvent e) {
				if(promocao.getFoto() !=null && !promocao.getFoto().getLocal_foto().equals("")){
					CtrFotoVer ctr= new CtrFotoVer(controle, promocao.getFoto());
					ctr.setVisible(true);
					form.setVisible(false);
				}else{
					JOptionPane.showMessageDialog(null, "Nenhuma foto adicionada", "Nenhuma foto adicionada",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		//botao adicionar foto
		form.getAdicionarFoto().addActionListener(new ActionListener() {//OK
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
							form.getTxtFoto().setText(fc.getSelectedFile().getAbsolutePath());
							promocao.setFoto(new Foto(fc.getSelectedFile().getAbsolutePath()));
				}
			}
		});
	}
	private Promocao preencherPromocao() {
		Promocao promocao = new Promocao();
		promocao.setNome(form.getTxtNome().getText());
		SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
		Date dataInicio = null;
		try { 
		    dataInicio = formataData.parse(form.getTxtDataInico().getText());
		} catch (ParseException ex) {
		    System.out.println("Erro ao formatar Data de Início"+ex);
		}
		Calendar cal = Calendar.getInstance();
	    cal.setTime(dataInicio);
		promocao.setDataInicio(cal);
		String validadeString = form.getTxtValidade().getText().replaceAll("[/ ]", "");
		if(!validadeString.equals("")){
			//NAO VALIDA AQUI pq ja deve estar validada
			try {
				Date validade = formataData.parse(form.getTxtValidade().getText());
			} catch (ParseException e) {
				System.out.println("Erro ao formatar data de Validade");
			}
			Calendar val = Calendar.getInstance();
			promocao.setValidade(val);
		}
		promocao.setDescricao(form.getTxtAreaDescricao().getText());
		//Foto f = new Foto
		return promocao;
	}
	@SuppressWarnings("static-access")
	private List <String> validarCampos(){
		List<String> erros = new ArrayList <String>();
		MySqlPromocaoDAO tamanhos = new MySqlPromocaoDAO();
		MySqlFotoDAO tamanhoFoto = new MySqlFotoDAO();
		//validar só: nome, dataInicio, descricao e local_foto
		if(form.getTxtNome().getText() == null 
				|| form.getTxtNome().getText().equals("")){
			erros.add("Nome é um campo obrigatório");
		}else if(form.getTxtNome().getText().length() > tamanhos.TAMANHO_NOME){
			erros.add("Nome muito grande");
		}
		String data = form.getTxtDataInico().getText().replaceAll("[/ ]", "");
		Calendar dataAtual = Calendar.getInstance();
		if(data.equals("")){//FIXME melhorar essa validação
			erros.add("Data de Inicio é obrigatória");
			
		}else {
			String ano = form.getTxtDataInico().getText().substring(6);
			
			if(Integer.parseInt(ano) < dataAtual.get(Calendar.YEAR) 
					|| Integer.parseInt(ano) > dataAtual.get(Calendar.YEAR)){//TODO testar qual ano q esse year pega
				erros.add("Data de Início inválida");
			}
		}
		String validade = form.getTxtValidade().getText().replaceAll("[/ ]", "");
		if(!validade.equals("")){//FIXME melhorar essa validação
			String ano = form.getTxtValidade().getText().substring(6);
			if(Integer.parseInt(ano) < dataAtual.get(Calendar.YEAR)){//se ano for menor que ano atual
				erros.add("Validade Inválida");
			}
		}
		if(form.getTxtAreaDescricao().getText() == null
				|| form.getTxtAreaDescricao().getText().equals("")) {
			erros.add("Descrição é obrigatória");
		}else if(form.getTxtAreaDescricao().getText().length() > tamanhos.TAMANHO_DESCRICAO){
			erros.add("Descrição muito grande");
		}
		if(!form.getTxtFoto().getText().equals("")){
			//caso tenha uma foto, verifica se seu caminho é válido
			if(form.getTxtFoto().getText().length() > tamanhoFoto.TAMANHO_LOCAL_FOTO){
				erros.add("Caminho para a foto muito grande");
			}
		}
			
		
		return erros;
	}
	@Override
	public void setVisible(boolean b) {
		if(promocao.getItens() != null && !promocao.getItens().isEmpty()){
			ItemTableModel model = new ItemTableModel(promocao.getItens(),true);
			form.getTabelaItens().setModel(model);
			TableColumn column = null;
		    //fixa largura das colunas
			for(int i = 0; i < 2; i++) {
		      column = form.getTabelaItens().getColumnModel().getColumn(i);
		      if(i == 0){
		        column.setPreferredWidth(160);//nome
		      }else if(i == 1){
		        column.setPreferredWidth(300);//descricao
		      }else {
		    	  column.setPreferredWidth(50);//preco
		      }
		    }
		}		
		form.setVisible(b);
	}
	public Promocao getPromocao(){
		return promocao;
	}
}
