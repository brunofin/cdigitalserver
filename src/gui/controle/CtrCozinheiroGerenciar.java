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
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import bean.Cozinheiro;
import bean.Endereco;
import bean.Foto;

import dao.cozinheiro.CozinheiroDAO;
import dao.factory.DAOFactory;
import dao.factory.Database;
import gui.modelo.FrmCozinheiroGerenciar;

public class CtrCozinheiroGerenciar implements Controle {
	private FrmCozinheiroGerenciar form;
	private Controle ctrParent;
	private CozinheiroDAO cozinheiroDAO;
	private DAOFactory factory;
	private Cozinheiro cozinheiro;
	//cozinheiro
	private final int tamanhoNome = 38;
	private final int tamanhoSobrenome = 78;
	private final int tamanhoDataNascimento = 13;
	private final int tamanhoCpf = 14;
	private final int tamanhoRg = 8;
	private final int tamanhoTelefone = 12;
	private final int tamanhoCelular = 12;
	private final int tamanhoEspecialidade = 98;
	private final int tamanhoHistorico = 298;
	//endereco
	private final int tamanhoCep = 8;
	private final int tamanhoNumero = 8;
	private final int tamanhoRua = 58;
	private final int tamanhoEstado = 2;
	private final int tamanhoCidade = 58;
	private final int tamanhoBairro = 58;
	//foto
	private final int tamanhoFoto = 398;
	
	public CtrCozinheiroGerenciar(Controle ctrParent){
		this.ctrParent = ctrParent;
		form = new FrmCozinheiroGerenciar();
		configurar();
		adicionarListeners();
	}
	private void configurar(){
		form.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		cozinheiro = new Cozinheiro();
		//combo estados
		form.getComboBoxEstados().setModel(new DefaultComboBoxModel(new String[] {
				"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA",
				"MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN",
				"RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
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
				form.dispose();
			}
		});
		// Botão OK
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
					return;
				}
				JOptionPane.showMessageDialog(null, "Cozinheiro Não Inserido!", "Erro",JOptionPane.ERROR_MESSAGE);
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
		endereco.setEstado((String)form.getComboBoxEstados().getSelectedItem());//ok
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
	private List <String> validarCampos(){
		List <String> campos = new ArrayList<String>();
		if(form.getTxtNome().getText() == null 
				|| form.getTxtNome().getText().equals("")) {
			if(form.getTxtNome().getText().length()>tamanhoNome){
				campos.add("Nome muito grande");
			}else{
				campos.add("Nome é obrigatório");
			}	
		}
		if(form.getTxtSobrenome().getText() == null 
				|| form.getTxtSobrenome().getText().equals("")){
			if(form.getTxtSobrenome().getText().length()>tamanhoSobrenome){
				campos.add("Sobrenome muito Grande");
			}else{
				campos.add("Sobrenome é obrigatório");
			}	
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
			if(form.getTxtTelefone().getText().length()>tamanhoTelefone){
				campos.add("Telefone muito grande");
			}else{
				campos.add("Telefone é obrigatório");
			}
		}
		if(form.getTxtCelular().getText()==null || form.getTxtCelular().getText().equals("")){
			if(form.getTxtCelular().getText().length()>tamanhoCelular){
				campos.add("Celular muito Grande");
			}else{
				campos.add("Celular é obrigatório");
			}	
		}
		if(form.getTxtAreaHistorico().getText() == null || form.getTxtAreaHistorico().getText().equals("")){
			if(form.getTxtAreaHistorico().getText().length()>tamanhoHistorico){
				campos.add("Histórico muito grande");
			}
			campos.add("Histórico é obrigatório");
		}
		if(form.getTxtAreaEspecialidade().getText()== null || form.getTxtAreaEspecialidade().getText().equals("")){
			if(form.getTxtAreaEspecialidade().getText().length()>tamanhoEspecialidade){
				campos.add("Especialidade muito grande");
			}
			campos.add("Especialidade é obrigatória");
		}
		if(form.getTxtCep().getText() == null || form.getTxtCep().getText().equals("")){
			if(form.getTxtCep().getText().length()>tamanhoCep){
				campos.add("CEP muito grande");
			}
			campos.add("CEP é obrigatório");
		}
		if(form.getTxtNumero().getText()== null || form.getTxtNumero().getText().equals("")){
			if(form.getTxtNumero().getText().length()>tamanhoNumero){
				campos.add("Número muito grande");
			}
			campos.add("Número é obrigatório");
		}
		if(form.getTxtRua().getText() == null || form.getTxtRua().getText().equals("")){
			if(form.getTxtRua().getText().length()>tamanhoRua){
				campos.add("nome da rua muito grande");
			}
			campos.add("Rua é obrigatória");
		}
		if(form.getTxtCidade().getText() == null || form.getTxtCidade().getText().equals("")){
			if(form.getTxtCidade().getText().length()>tamanhoCidade){
				campos.add("nome da cidade muito grande");
			}
			campos.add("Cidade é obrigatória");
		}
		if(form.getTxtBairro().getText() == null || form.getTxtBairro().getText().equals("")){
			if(form.getTxtBairro().getText().length()>tamanhoBairro){
				campos.add("nome do bairro muito grande");
			}
			campos.add("Bairro é obrigatório");
		}
		if(form.getTxtFoto().getText() == null || form.getTxtFoto().getText().equals("")){
			if(form.getTxtFoto().getText().length()>tamanhoFoto){
				campos.add("caminho da foto muito grande");
			}
			campos.add("Foto é obrigatória");
		}
		return campos;
	}
	@Override
	public void setVisible(boolean b) {
		form.setVisible(b);
		
	}

}
