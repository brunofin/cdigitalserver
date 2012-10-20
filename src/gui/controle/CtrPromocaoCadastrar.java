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
import dao.promocao.PromocaoDAO;

import gui.modelo.FrmPromocaoCadastrar;


public class CtrPromocaoCadastrar implements Controle {
	private FrmPromocaoCadastrar form;
	private Controle ctrParent;
	private Promocao promocao;
	private List <Item> todosOsItens;
	private ItemDAO itemDAO;
	private PromocaoDAO promocaoDAO;
	private int idPromocao,idFoto;
	private boolean editando = false;
	
	public CtrPromocaoCadastrar(Controle ctrParent, boolean isEditar, Promocao promocaoParaEdicao){
		this.ctrParent = ctrParent;
		form = new FrmPromocaoCadastrar();
		editando = isEditar;
		if(isEditar){
			promocao = promocaoParaEdicao;
			idPromocao = promocaoParaEdicao.getPromocaoId();
			idFoto = promocaoParaEdicao.getFoto().getFotoId();
			form.getLimparButton().setVisible(false);
			form.getOkButton().setText("Atualizar");
			preencherCamposTela();
		}
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
			// TODO Auto-generated catch blo
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
		//botao Remover Foto
		form.getBtnRemoverFoto().addActionListener(new ActionListener(){//OK

			@Override
			public void actionPerformed(ActionEvent e) {
//				if(promocao.getFoto()!= null 
//						&& promocao.getFoto().getLocal_foto() != null 
//						&& !promocao.getFoto().getLocal_foto().equals("")){
				if(form.getTxtFoto().getText()!=null 
						&& !form.getTxtFoto().getText().equals("")){
					//promocao.setFoto(new Foto(""));
					form.getTxtFoto().setText("");
				}else{
					JOptionPane.showMessageDialog
					(null, "Nenhuma imagem cadastrada, "
							, "Nenhum imagem Cadastrada", 
							JOptionPane.WARNING_MESSAGE);
				}
				
			}
			
		});
		//botao OK
		form.getOkButton().addActionListener(new ActionListener(){//TODO modificar para atualizar tb
			@Override
			public void actionPerformed(ActionEvent e) {
				
				List <String> erros = validarCampos();
				if(!erros.isEmpty()){
					StringBuffer mensagem = new StringBuffer("Erro no(s) seguinte(s) campo(s):");
					for(String s : erros){
						mensagem.append("\n"+s);
					}
					JOptionPane.showMessageDialog(null,  mensagem.toString(), "Erro", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//prenche campos já validados
				preencherPromocao();
				
				//caso esteja editando
				if(editando){//TODO testar
					DAOFactory factory = DAOFactory.getDaoFactory(Database.MYSQL);
					promocaoDAO = factory.getPromocaoDAO();
					promocao.setPromocaoId(idPromocao);
					promocao.getFoto().setFotoId(idFoto);
					boolean alterou = false;
					try {
						alterou = promocaoDAO.alterar(promocao);
					} catch (SQLException e1) {
						System.out.println("Erro ao alterar Promoção!"+e1);
					}
					if(alterou){
						JOptionPane.showMessageDialog(null, "Promoção Editada!");
						ctrParent.setVisible(true);//chama o dao para atualizar a lista
						form.dispose();
					}
					return;
				}
				//insere no banco
				DAOFactory factory = DAOFactory.getDaoFactory(Database.MYSQL);
				promocaoDAO = factory.getPromocaoDAO();
				int inseriu = 0;
				try {
					inseriu = promocaoDAO.incluir(promocao);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao cadastrar promoção no Banco"
							,"Erro", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(inseriu > 0){//TODO testar
					JOptionPane.showMessageDialog(null, "Nova promoção cadastrada com sucesso!"
							,"Sucesso",JOptionPane.INFORMATION_MESSAGE);
					ctrParent.setVisible(true);//chama o dao para atualizar a lista
					form.dispose();
					return;
				}else{
					JOptionPane.showMessageDialog(null, "Promoção não cadastrada"
							,"Erro", JOptionPane.WARNING_MESSAGE);
				}
				
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
		form.getBtnVerFoto().addActionListener(new ActionListener() {//ok
			@Override
			public void actionPerformed(ActionEvent e) {
//				if(promocao.getFoto() !=null 
//						&& promocao.getFoto().getLocal_foto() != null 
//						&& !promocao.getFoto().getLocal_foto().equals("")){
				if(form.getTxtFoto().getText()!=null 
						&& !form.getTxtFoto().getText().equals("")){
					Foto f = new Foto(form.getTxtFoto().getText());
					CtrFotoVer ctr= new CtrFotoVer(controle, f);
					ctr.setVisible(true);
					form.setVisible(false);
				}else{
					JOptionPane.showMessageDialog
					(null, "Nenhuma foto adicionada", "Nenhuma foto adicionada",
							JOptionPane.WARNING_MESSAGE);
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
							//promocao.setFoto(new Foto(fc.getSelectedFile().getAbsolutePath()));
				}
			}
		});
	}
	/**
	 * Preenche os dados obrigatórios da promoção
	 * (nome, data de inicio e descrição)
	 * sem validar e verifica os outros (validade,
	 * local_foto), caso nao 
	 * sejam nulos seta eles também
	 * 
	 */
	private void preencherPromocao() {
		
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
		if(form.getTxtFoto().getText() != null && 
				!form.getTxtFoto().getText().equals("")){
			promocao.setFoto(new Foto(form.getTxtFoto().getText()));
		}
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
			carregarItensPromocao();
		}		
		form.setVisible(b);
	}
	private void carregarItensPromocao(){
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
	public Promocao getPromocao(){
		return promocao;
	}
	private void preencherCamposTela(){
		form.getTxtNome().setText(promocao.getNome());
		SimpleDateFormat formatoData = new SimpleDateFormat("ddMMyyyy");
		String dataInicio = formatoData.format(promocao.getDataInicio().getTime());
		form.getTxtDataInico().setText(dataInicio);
		if(promocao.getValidade()!=null){
			form.getTxtValidade().setText(formatoData.format(promocao.getValidade().getTime()));
		}
		form.getTxtAreaDescricao().setText(promocao.getDescricao());
		if(promocao.getFoto()!=null 
				&& promocao.getFoto().getLocal_foto()!=null
				&& !promocao.getFoto().getLocal_foto().equals("")){
			form.getTxtFoto().setText(promocao.getFoto().getLocal_foto());
		}
		if(promocao.getItens()!=null && !promocao.getItens().isEmpty()){
			carregarItensPromocao();
		}
	}
}
