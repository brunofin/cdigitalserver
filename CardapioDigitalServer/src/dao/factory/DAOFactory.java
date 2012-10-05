package dao.factory;

import dao.categoria.CategoriaDAO;
import dao.cliente.ClienteDAO;
import dao.comentario.ComentarioDAO;
import dao.cozinheiro.CozinheiroDAO;
import dao.endereco.EnderecoDAO;
import dao.foto.FotoDAO;
import dao.ingrediente.IngredienteDAO;
import dao.item.ItemDAO;
import dao.itemingrediente.ItemIngredienteDAO;
import dao.itempedido.ItemPedidoDAO;
import dao.itempromocao.ItemPromocaoDAO;
import dao.pedido.PedidoDAO;
import dao.promocao.PromocaoDAO;
import dao.tipo.TipoDAO;

// exemplo de utilização: DAOFactory dao = DAOFactory.getDaoFactory(Database.MYSQL);
public abstract class DAOFactory {

	public abstract ClienteDAO getClienteDAO();
	public abstract ComentarioDAO getComentarioDAO();
	public abstract CozinheiroDAO getCozinheiroDAO();
	public abstract EnderecoDAO getEnderecoDAO();
	public abstract FotoDAO getFotoDAO();
	public abstract IngredienteDAO getIngredienteDAO();
	public abstract PedidoDAO getPedidoDAO();
	public abstract ItemDAO getItemDAO();
	public abstract PromocaoDAO getPromocaoDAO();
	public abstract TipoDAO getTipoDAO();
	public abstract CategoriaDAO getCategoriaDAO();
	public abstract ItemIngredienteDAO getItemIngredienteDAO();
	public abstract ItemPedidoDAO getItemPedidoDAO();
	public abstract ItemPromocaoDAO getItemPromocaoDAO();

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