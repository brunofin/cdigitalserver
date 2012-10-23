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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import util.Estado;

import bean.Cozinheiro;
import bean.Endereco;
import bean.Foto;

import dao.cozinheiro.CozinheiroDAO;
import dao.cozinheiro.MySqlCozinheiroDAO;
import dao.endereco.MySqlEnderecoDAO;
import dao.factory.DAOFactory;
import dao.factory.Database;
import dao.foto.MySqlFotoDAO;
import gui.modelo.FrmCozinheiroGerenciar;

public class CtrCozinheiroGerenciar implements Controle {
	private FrmCozinheiroGerenciar form;
	private Controle parent;
	private CozinheiroDAO cozinheiroDAO;
	private DAOFactory factory;
	private Cozinheiro cozinheiro;
	private boolean editando;
	private int idCozinheiro,idFoto,idEndereco;
	
	public CtrCozinheiroGerenciar(Controle ctrParent, boolean isEditar, Cozinheiro cozinheiroParaEditar){
		this.parent = ctrParent;
		form = new FrmCozinheiroGerenciar();
		//case a tela tenha sido chamada pelo botão editar
		//seta o cozinheiro para editar
		editando = isEditar;
		if(isEditar){
			cozinheiro = cozinheiroParaEditar;
			idCozinheiro = cozinheiro.getId();
			idFoto = cozinheiro.getFoto().getFotoId();
			idEndereco = cozinheiro.getEndereco().getEnderecoId();
			form.getLimparButton().setVisible(false);
			form.getOkButton().setText("Atualizar");
			preencherCamposTela();
		}
		configurar();
		adicionarListeners();
	}
	private void configurar(){
		form.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		cozinheiro = new Cozinheiro();
		
		form.getComboBoxEstados().setModel(new DefaultComboBoxModel<Estado>(Estado.values()));
		//combo estados
//		form.getComboBoxEstados().setModel(new DefaultComboBoxModel(new String[] {
//				"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA",
//				"MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN",
//				"RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
	}
	private void adicionarListeners() {
		final Controle controle = this;
		// Botão limpar
		form.getLimparButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				form.getTxtNome().setText("");
				form.getTxtSobrenome().setText("");
				form.getTxtDataNascimento().setText("");
				form.getTxtCpf().setText("");
				form.getTxtRg().setText("");
				form.getTxtTelefone().setText("");
				form.getTxtCelular().setText("");
				form.getTxtAreaHistorico().setText("");
				form.getTxtAreaEspecialidade().setText("");
				form.getTxtFoto().setText("");
				//endereço
				form.getTxtCep().setText("");
				form.getTxtNumero().setText("");
				form.getTxtRua().setText("");
				form.getTxtCidade().setText("");
				form.getTxtBairro().setText("");
			}
		});
				
		// Botão cancelar
		form.getCancelButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.setVisible(true);
				form.dispose();
			}
		});
		// Botão OK ou Atualizar
		form.getOkButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				List <String> listaErros = validarCampos();
				
				if(listaErros.size()>0){
					//Caso tenh algum campo inválido
					StringBuffer mensagem = new StringBuffer("Erro no(s) seguinte(s) campo(s):");
					for(String s : listaErros){
						mensagem.append("\n"+s);
					}
					JOptionPane.showMessageDialog(null,  mensagem.toString(), "Erro", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//caso esteja editado
				if(editando){
					factory = DAOFactory.getDaoFactory(Database.MYSQL);
					cozinheiroDAO = factory.getCozinheiroDAO();
					cozinheiro = preencherCozinheiro();
					cozinheiro.setId(idCozinheiro);
					cozinheiro.getFoto().setFotoId(idFoto);
					cozinheiro.getEndereco().setEnderecoId(idEndereco);
					boolean alterou = false;
					try {
						alterou = cozinheiroDAO.alterar(cozinheiro);
					} catch (SQLException e1) {
						System.out.println("Erro ao alterar Cozinheiro!"+e1);
					}
					if(alterou){
						JOptionPane.showMessageDialog(null, "Cozinheiro Editado!");
						parent.setVisible(true);//chama o dao para atualizar a lista
						form.dispose();
					}
					return;
				}
				cozinheiro = preencherCozinheiro();
				factory = DAOFactory.getDaoFactory(Database.MYSQL);
				cozinheiroDAO = factory.getCozinheiroDAO();
				int inseriu = 0;
				try {
					inseriu = cozinheiroDAO.incluir(cozinheiro);
				} catch (SQLException e1) {
					System.out.println("Erro ao inserir Cozinheiro!"+e1);
				}
				if(inseriu > 0){
					JOptionPane.showMessageDialog(null, "Cozinheiro Inserido!");
					parent.setVisible(true);//chama o dao para atualizar a lista
					form.dispose();
					return;
				}
				JOptionPane.showMessageDialog(null, "Cozinheiro Não Inserido!", "Erro",JOptionPane.ERROR_MESSAGE);
			}
		});
		//botao excluir foto
		form.getBtnExcluir().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(form.getTxtFoto().getText() != null 
						&& !form.getTxtFoto().getText().equals("")){
					form.getTxtFoto().setText("");
				}else{
					JOptionPane.showMessageDialog
					(null, "Nenhuma imagem cadastrada, "
							, "Nenhum imagem Cadastrada", 
							JOptionPane.WARNING_MESSAGE);
				}
			}
			
		});
		//botao ver foto
		form.getBtnVer().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(form.getTxtFoto().getText() != null
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
		form.getBtnAdicionarFoto().addActionListener(new ActionListener() {
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
				}
			}
		});
		
	}
	/**
	 * Preenche dados do cozinheiro (precisam ser validados antes)
	 */
	private Cozinheiro preencherCozinheiro(){
		Cozinheiro cozinheiro = new Cozinheiro();
		cozinheiro.setNome(form.getTxtNome().getText());
		cozinheiro.setSobrenome(form.getTxtSobrenome().getText());
		SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
		Date dataNasc = null;
		try { 
		    dataNasc = formataData.parse(form.getTxtDataNascimento().getText());
		} catch (ParseException ex) {
		    System.out.println("Erro ao formatar Data"+ex);
		}
		Calendar cal = Calendar.getInstance();
	    cal.setTime(dataNasc);
		cozinheiro.setDataNascimento(cal);
		cozinheiro.setCpf(tiraMascaraCpfRg(form.getTxtCpf().getText()));//OK
		cozinheiro.setRg(tiraMascaraCpfRg(form.getTxtRg().getText()));//OK
		cozinheiro.setTelefone(form.getTxtTelefone().getText());
		cozinheiro.setCelular(form.getTxtCelular().getText());
		cozinheiro.setHistorico(form.getTxtAreaHistorico().getText());
		cozinheiro.setEspecialidade(form.getTxtAreaEspecialidade().getText());
		Endereco endereco = new Endereco();
		endereco.setCep(form.getTxtCep().getText());
		endereco.setNumero(form.getTxtNumero().getText());
		endereco.setRua(form.getTxtRua().getText());
		endereco.setEstado((Estado)form.getComboBoxEstados().getSelectedItem());//ok
		endereco.setCidade(form.getTxtCidade().getText());
		endereco.setBairro(form.getTxtBairro().getText());
		cozinheiro.setEndereco(endereco);
		cozinheiro.setFoto(new Foto(form.getTxtFoto().getText()));
		return cozinheiro;
	}
	/**
	 * Tira máscara do CPF e do RG
	 * @param cpfOuRg
	 * @return
	 */
	private String tiraMascaraCpfRg(String cpfOuRg){
		return cpfOuRg.replaceAll("[.-]", "");
	}
	/**
	 * Valida todos os campos obrigatórios
	 * @return
	 */
	@SuppressWarnings("static-access")
	private List <String> validarCampos(){
		List <String> campos = new ArrayList<String>();
		MySqlCozinheiroDAO tamanhosCampos = new MySqlCozinheiroDAO();
		MySqlEnderecoDAO tamanhosEndereco = new MySqlEnderecoDAO();
		MySqlFotoDAO tamanhoFoto = new MySqlFotoDAO();
		
		if(form.getTxtNome().getText() == null 
				|| form.getTxtNome().getText().equals("")) {
			campos.add("Nome é obrigatório");
		}else if(form.getTxtNome().getText().length()>tamanhosCampos.TAMANHO_NOME){
			campos.add("Nome muito grande");
		}
		if(form.getTxtSobrenome().getText() == null 
				|| form.getTxtSobrenome().getText().equals("")){		
			campos.add("Sobrenome é obrigatório");		
		}else if(form.getTxtSobrenome().getText().length()>tamanhosCampos.TAMANHO_SOBRENOME){
			campos.add("Sobrenome muito Grande");
		}
		String data = form.getTxtDataNascimento().getText().replaceAll("[/ ]", "");
		if(data.equals("")){
			campos.add("Data é obrigatória");
			
		}else {
			String ano = form.getTxtDataNascimento().getText().substring(6);
			if(Integer.parseInt(ano) < 1910){//FIXME melhorar isso
				campos.add("Data inválida");
			}
		}
		String cpf = form.getTxtCpf().getText().replaceAll("[-. ]", "");
		if(cpf.equals("")){
		//if(form.getTxtCpf().getText()==null ||form.getTxtCpf().getText().equals("")){//mascara deixa tamanho certo
			campos.add("CPF é obrigatório");
		}
		String rg = form.getTxtRg().getText().replaceAll("[-. ]", "");
		if(rg.equals("")){
		//if(form.getTxtRg().getText()== null || form.getTxtRg().getText().equals("")){//mascara deixa tamanho certo
			campos.add("RG é obrigatório");
		}
		if(form.getTxtTelefone().getText()==null || form.getTxtTelefone().getText().equals("")){
			campos.add("Telefone é obrigatório");
		}else if(form.getTxtTelefone().getText().length()>tamanhosCampos.TAMANHO_TELEFONE){
			campos.add("número de telefone muito grande");
		}
		if(form.getTxtCelular().getText()==null || form.getTxtCelular().getText().equals("")){
			campos.add("Celular é obrigatório");	
		}else if(form.getTxtCelular().getText().length()>tamanhosCampos.TAMANHO_CELULAR){
			campos.add("número de celular muito Grande");
		}
		if(form.getTxtAreaHistorico().getText() == null || form.getTxtAreaHistorico().getText().equals("")){
			campos.add("Histórico é obrigatório");
		}else if(form.getTxtAreaHistorico().getText().length()>tamanhosCampos.TAMANHO_HISTORICO){
			campos.add("Histórico muito grande");
		}
		if(form.getTxtAreaEspecialidade().getText()== null || form.getTxtAreaEspecialidade().getText().equals("")){
			campos.add("Especialidade é obrigatória");
		}else if(form.getTxtAreaEspecialidade().getText().length()>tamanhosCampos.TAMANHO_ESPECIALIDADE){
			campos.add("Especialidade muito grande");
		}
		if(form.getTxtCep().getText() == null || form.getTxtCep().getText().equals("")){
			campos.add("CEP é obrigatório");
		}else if(form.getTxtCep().getText().length()>tamanhosEndereco.TAMANHO_CEP){
			campos.add("CEP muito grande");
		}
		if(form.getTxtNumero().getText()== null || form.getTxtNumero().getText().equals("")){
			campos.add("Número é obrigatório");
		}else if(form.getTxtNumero().getText().length()>tamanhosEndereco.TAMANHO_NUMERO){
			campos.add("Número muito grande");
		}
		if(form.getTxtRua().getText() == null || form.getTxtRua().getText().equals("")){
			campos.add("Rua é obrigatória");
		}else if(form.getTxtRua().getText().length()>tamanhosEndereco.TAMANHO_RUA){
			campos.add("nome da rua muito grande");
		}
		if(form.getTxtCidade().getText() == null || form.getTxtCidade().getText().equals("")){
			campos.add("Cidade é obrigatória");
		}else if(form.getTxtCidade().getText().length()>tamanhosEndereco.TAMANHO_CIDADE){
			campos.add("nome da cidade muito grande");
		}
		if(form.getTxtBairro().getText() == null || form.getTxtBairro().getText().equals("")){
			campos.add("Bairro é obrigatório");
		}if(form.getTxtBairro().getText().length()>tamanhosEndereco.TAMANHO_BAIRRO){
			campos.add("nome do bairro muito grande");
		}
		if(form.getTxtFoto().getText() == null || form.getTxtFoto().getText().equals("")){
			campos.add("Foto é obrigatória");
		}if(form.getTxtFoto().getText().length()>tamanhoFoto.TAMANHO_LOCAL_FOTO){
			campos.add("caminho da foto muito grande");
		}
		return campos;
	}
	/**
	 * Método que preenche todos os campos da tela 
	 * com os dados do cozinheiro que será editado
	 */
	private void preencherCamposTela() {
		form.getTxtNome().setText(cozinheiro.getNome());
		form.getTxtSobrenome().setText(cozinheiro.getSobrenome());
		SimpleDateFormat formatoData = new SimpleDateFormat("ddMMyyyy");
		String dataNasc = formatoData.format(cozinheiro.getDataNascimento().getTime());
		form.getTxtDataNascimento().setText(dataNasc);
		form.getTxtCpf().setText(cozinheiro.getCpf());
		form.getTxtRg().setText(cozinheiro.getRg());
		form.getTxtTelefone().setText(cozinheiro.getTelefone());
		form.getTxtCelular().setText(cozinheiro.getCelular());
		form.getTxtAreaHistorico().setText(cozinheiro.getHistorico());
		form.getTxtAreaEspecialidade().setText(cozinheiro.getEspecialidade());
		//endereco
		form.getTxtCep().setText(cozinheiro.getEndereco().getCep());
		form.getTxtNumero().setText(cozinheiro.getEndereco().getNumero());
		form.getTxtRua().setText(cozinheiro.getEndereco().getRua());
		
		form.getComboBoxEstados().setSelectedItem(cozinheiro.getEndereco().getEstado());//OK
		form.getTxtCidade().setText(cozinheiro.getEndereco().getCidade());
		form.getTxtBairro().setText(cozinheiro.getEndereco().getBairro());
		form.getTxtBairro().setText(cozinheiro.getEndereco().getBairro());
		form.getTxtFoto().setText(cozinheiro.getFoto().getLocal_foto());
	}
	@Override
	public void setVisible(boolean b) {
		form.setVisible(b);
		
	}

}
