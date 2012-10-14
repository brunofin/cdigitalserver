package gui.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JDialog;

import bean.Promocao;

import dao.foto.MySqlFotoDAO;
import dao.promocao.MySqlPromocaoDAO;

import gui.modelo.FrmPromocaoCadastrar;


public class CtrPromocaoCadastrar implements Controle {
	private FrmPromocaoCadastrar form;
	private Controle ctrParent;
	private Promocao promocao;
	
	public CtrPromocaoCadastrar(Controle ctrParent){
		this.ctrParent = ctrParent;
		form = new FrmPromocaoCadastrar();
		configurar();
		adicionarListeners();
	}
	
	private void configurar(){
		form.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	private void adicionarListeners(){
		// Botão cancelar
		form.getCancelButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				form.dispose();
			}
		});
		// botão limpar
		form.getLimparButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				form.getTxtFoto().setText("");
				form.getTxtNome().setText("");
				form.getTxtDataInico().setText("");
				form.getTxtValidade().setText("");
				form.getTxtAreaDescricao().setText("");
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
		form.setVisible(b);
	}

}
