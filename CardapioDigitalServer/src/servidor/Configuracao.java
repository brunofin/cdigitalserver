package servidor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import dao.factory.Database;

public class Configuracao implements Serializable {
	private int cardapioPorta;
	private Database dbBanco;
	private String dbIp;
	private int dbPorta;
	private String dbUsuario;
	private String dbSenha;
	private static String filename = "servidor.cfg";
	
	public static Database DB_SELECIONADO;
	public static int DB_PORTA;
	public static String DB_ENDERECO;
	public static int CARDAPIO_PORTA;
	
	public void salvar() throws IOException {
		File f = new File(filename);
		System.out.println("<Configuracao> Escrevendo no arquivo...");
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
		oos.writeObject(this);
		
		DB_SELECIONADO = getDbBanco();
		DB_PORTA = getDbPorta();
		DB_ENDERECO = getDbIp();
		CARDAPIO_PORTA = getCardapioPorta();
		
		oos.close();
	}
	
	public void ler() throws IOException, ClassNotFoundException {
		File f = new File(filename);
		System.out.println("<Configuracao> Lendo do arquivo...");
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
		Configuracao aux = (Configuracao) ois.readObject();
		
		
		setCardapioPorta(aux.getCardapioPorta());
		setDbBanco(aux.getDbBanco());
		setDbIp(aux.getDbIp());
		setDbPorta(aux.getDbPorta());
		setDbUsuario(aux.getDbUsuario());
		setDbSenha(aux.getDbSenha());
		
		DB_SELECIONADO = getDbBanco();
		DB_PORTA = getDbPorta();
		DB_ENDERECO = getDbIp();
		CARDAPIO_PORTA = getCardapioPorta();
		
		ois.close();
	}

	public int getCardapioPorta() {
		return cardapioPorta;
	}

	public void setCardapioPorta(int cardapioPorta) {
		this.cardapioPorta = cardapioPorta;
	}

	public Database getDbBanco() {
		return dbBanco;
	}

	public void setDbBanco(Database dbBanco) {
		this.dbBanco = dbBanco;
	}

	public String getDbIp() {
		return dbIp;
	}

	public void setDbIp(String dbIp) {
		this.dbIp = dbIp;
	}

	public int getDbPorta() {
		return dbPorta;
	}

	public void setDbPorta(int dbPorta) {
		this.dbPorta = dbPorta;
	}

	public String getDbUsuario() {
		return dbUsuario;
	}

	public void setDbUsuario(String dbUsuario) {
		this.dbUsuario = dbUsuario;
	}

	public String getDbSenha() {
		return dbSenha;
	}

	public void setDbSenha(String dbSenha) {
		this.dbSenha = dbSenha;
	}

	public static String getFilename() {
		return filename;
	}

	public static void setFilename(String filename) {
		Configuracao.filename = filename;
	}
	
	
}
