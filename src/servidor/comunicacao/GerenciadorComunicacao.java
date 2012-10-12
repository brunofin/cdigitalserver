package servidor.comunicacao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import bean.Categoria;
import bean.Foto;
import bean.Item;
import bean.Pedido;
import bean.Tipo;

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
 * Cada método deve ser listado no enum <code>Metodo</code>, e seu respectivo método
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
 * @see servidor.comunicacao.Metodo
 */
public class GerenciadorComunicacao {
	
	public Object processa(Conexao c, Pacote pacote) {
		switch(pacote.getMetodo()) {
		case LISTAR_ITEMS:
			return listarItems();
		case LISTAR_TIPOS:
			return listarTipos();
		case LISTAR_CATEGORIAS:
			return listarCategorias();
		case BAIXAR_FOTO:
			Foto f = (Foto) pacote.getArgumentos();
			return baixarFoto(f);
		
		case FAZER_PEDIDO:
			Pedido p = (Pedido) pacote.getArgumentos();
			return fazerPedido(p, c.getDispositivo());
		default:
			return null;
			
		}
	}
	
	/**
	 * @see servidor.comunicacao.Metodo.BAIXAR_FOTO
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
			resposta = new Pacote(Metodo.RESPOSTA, serialized);
			
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
	 * @see servidor.comunicacao.Metodo.LISTAR_ITEMS
	 * @return
	 */
	private Object listarItems() {
		DAOFactory factory = DAOFactory.getDaoFactory(Database.MYSQL);
		ItemDAO dao = factory.getItemDAO();
		
		Pacote resposta = null;
		
		try{
			List<Item> lista = dao.listar();
			resposta = new Pacote(Metodo.RESPOSTA, lista);
		} catch(SQLException e) {
			System.out.println("<GerenciadorComunicacao> Exceção em listarItems(): " + e.getMessage());
		}
		return resposta;
	}
	
	/**
	 * 
	 * @see servidor.comunicacao.Metodo.LISTAR_TIPOS
	 * @return
	 */
	private Object listarTipos() {
		DAOFactory factory = DAOFactory.getDaoFactory(Database.MYSQL);
		TipoDAO dao = factory.getTipoDAO();
		
		Pacote resposta = null;
		
		try{
			List<Tipo> lista = dao.listar();
			resposta = new Pacote(Metodo.RESPOSTA, lista);
		} catch(SQLException e) {
			System.out.println("<GerenciadorComunicacao> Exceção em listarTipos(): " + e.getMessage());
		}
		return resposta;
	}
	
	/**
	 * 
	 * @see servidor.comunicacao.Metodo.LISTAR_CATEGORIAS
	 * @return
	 */
	private Object listarCategorias() {
		DAOFactory factory = DAOFactory.getDaoFactory(Database.MYSQL);
		CategoriaDAO dao = factory.getCategoriaDAO();
		
		Pacote resposta = null;
		
		try{
			List<Categoria> lista = dao.listar();
			resposta = new Pacote(Metodo.RESPOSTA, lista);
		} catch(SQLException e) {
			System.out.println("<GerenciadorComunicacao> Exceção em listarCategorias(): " + e.getMessage());
		}
		return resposta;
	}
	
	/**
	 * @see servidor.comunicacao.Metodo.FAZER_PEDIDO
	 * @return
	 */
	private Object fazerPedido(Pedido p, Dispositivo d) {
		// TODO: enviar os pedidos para a tela do servidor junto com o numero da mesa (em Dispositivo)
		
		return null;
	}
}