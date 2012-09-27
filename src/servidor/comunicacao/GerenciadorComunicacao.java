package servidor.comunicacao;

import java.sql.SQLException;

import dao.factory.DAOFactory;
import dao.factory.Database;
import dao.item.ItemDAO;
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
		LISTAR_ITEMS;
	}
	
	public Object processa(Conexao c, Pacote pacote) {
		switch(pacote.getMetodo()) {
		case LISTAR_ITEMS:
			return listarItems();
			
		default:
			return null;
			
		}
		
	}
	
	private Object listarItems() {
		DAOFactory factory = DAOFactory.getDaoFactory(Database.MYSQL);
		ItemDAO dao = factory.getItemDAO();
		
		Object resposta = null;
		
		try{
			resposta = dao.listar();
		} catch(SQLException e) {
			System.out.println("<GerenciadorComunicacao> Exceção em listarItems(): " + e.getMessage());
		}
		return resposta;
	}
}