package servidor.comunicacao;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import bean.Foto;

import dao.categoria.CategoriaDAO;
import dao.factory.DAOFactory;
import dao.factory.Database;
import dao.item.ItemDAO;
import dao.tipo.TipoDAO;
import servidor.conexao.Conexao;

/**
 * Este é o ponto de comunicação entre clientes (Android) e servidor.
 * Cliente manda para o servidor um objeto do tipo <code>Pacote</code> que contém
 * qual método deverá ser chamado, e seus respectivos argumentos.
 * 
 * Cada método deve ser listado no enum <code>METODO</code>, e seu respectivo método
 * implementado nesta classe. O método <code>processa</code> irá descobrir
 * qual método está sendo invocado através do enum e então chamá-lo, repassando
 * os respectivos argumentos. O único método público dessa classe deve ser <code>processa</code>.
 * 
 * A resposta é enviada de volta ao cliente usando o retorno do método <code>processa</code>, que é recebida
 * pela <code>Conexao</code>, que então envia utilizando a <code>ObjectOutputStream</code> do cliente.
 * 
 * Métodos que não precisam enviar uma resposta ao cliente devem retornar <code>null</code>, que será tratado pela <code>Conexao</code>.
 * O cliente saberá qual tipo de resposta estará esperando, ou se estará esperando uma,
 * pois foi ele que requisitou o processamento do método.
 * 
 * @see servidor.comunicacao.Pacote
 * @see servidor.conexao.Conexao
 */
public class GerenciadorComunicacao {
	public enum METODO {
		RESPOSTA,
		BAIXAR_FOTO, LISTAR_ITEMS, LISTAR_FOTOS;
	}
	
	public Object processa(Conexao c, Pacote pacote) {
		switch(pacote.getMetodo()) {
		case LISTAR_ITEMS:
			return listarItems();
		case BAIXAR_FOTO:
			Foto f = (Foto) pacote.getArgumentos();
			return baixarFoto(f);
		default:
			return null;
			
		}
	}
	
	/**
	 * @see METODO.BAIXAR_FOTO
	 * @return
	 */
	private Object baixarFoto(Foto f) {
		Pacote resposta = null;
		try {
			File file = new File(f.getLocal_foto());
			FileInputStream fis = new FileInputStream(file);
			byte b[] = new byte[(int) file.length()];
			fis.read(b);
			
			List<Byte> serialized = new LinkedList<Byte>();
			for(int i = 0; i <b.length; i++) {
				serialized.add(b[i]);
			}
			resposta = new Pacote(METODO.RESPOSTA, serialized);
			
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return resposta;
	}
	
	
	
	/**
	 * 
	 * @see METODO.LISTAR_ITEMS
	 * @return
	 */
	private Object listarItems() {
		DAOFactory factory = DAOFactory.getDaoFactory(Database.MYSQL);
		ItemDAO idao = factory.getItemDAO();
		
		Object resposta = null;
		
		try{
			resposta = idao.listar();
		} catch(SQLException e) {
			System.out.println("<GerenciadorComunicacao> Exceção em listarItems(): " + e.getMessage());
		}
		return resposta;
	}
}