package dao.factory;

import dao.bebida.BebidaDAO;
import dao.cliente.ClienteDAO;
import dao.comentario.ComentarioDAO;
import dao.cozinheiro.CozinheiroDAO;
import dao.endereco.EnderecoDAO;
import dao.foto.FotoDAO;
import dao.ingrediente.IngredienteDAO;
import dao.pedido.PedidoDAO;
import dao.prato.PratoDAO;
import dao.promocao.PromocaoDAO;

// exemplo de utilização: DAOFactory dao = DAOFactory.getDaoFactory(Database.MYSQL);
public abstract class DAOFactory {
	
	public abstract BebidaDAO getBebidaDAO();
	public abstract ClienteDAO getClienteDAO();
	public abstract ComentarioDAO getComentarioDAO();
	public abstract CozinheiroDAO getCozinheiroDAO();
	public abstract EnderecoDAO getEnderecoDAO();
	public abstract FotoDAO getFotoDAO();
	public abstract IngredienteDAO getIngredienteDAO();
	public abstract PedidoDAO getPedidoDAO();
	public abstract PratoDAO getPratoDAO();
	public abstract PromocaoDAO getPromocaoDAO();

	/**
	 * 
	 * @param db Enum do tipo dao.factory.Database que dirá o tipo de banco de dados a ser usado
	 * @return Um DAO Factory para o banco de dados selecionado ou null se nenhum selecionado.
	 * @see dao.factory.Database
	 */
	public static DAOFactory getDaoFactory(Database db) {
		switch (db) {
		case MYSQL:
			return new MySqlDAOFactory();
		default:
			return null;
		}
	}
}