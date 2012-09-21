package dao.factory;

import dao.cliente.ClienteDAO;
import dao.cliente.MySqlClienteDAO;
import dao.comentario.ComentarioDAO;
import dao.comentario.MySqlComentarioDAO;
import dao.cozinheiro.CozinheiroDAO;
import dao.cozinheiro.MySqlCozinheiroDAO;
import dao.endereco.EnderecoDAO;
import dao.endereco.MySqlEnderecoDAO;
import dao.foto.FotoDAO;
import dao.foto.MySqlFotoDAO;
import dao.ingrediente.IngredienteDAO;
import dao.ingrediente.MySqlIngredienteDAO;
import dao.item.MySqlPratoDAO;
import dao.item.ItemDAO;
import dao.pedido.MySqlPedidoDAO;
import dao.pedido.PedidoDAO;
import dao.promocao.MySqlPromocaoDAO;
import dao.promocao.PromocaoDAO;

public class MySqlDAOFactory extends DAOFactory {

	@Override
	public ClienteDAO getClienteDAO() {
		return new MySqlClienteDAO();
	}

	@Override
	public ComentarioDAO getComentarioDAO() {
		return new MySqlComentarioDAO();
	}

	@Override
	public CozinheiroDAO getCozinheiroDAO() {
		return new MySqlCozinheiroDAO();
	}

	@Override
	public EnderecoDAO getEnderecoDAO() {
		return new MySqlEnderecoDAO();
	}

	@Override
	public FotoDAO getFotoDAO() {
		return new MySqlFotoDAO();
	}

	@Override
	public IngredienteDAO getIngredienteDAO() {
		return new MySqlIngredienteDAO();
	}

	@Override
	public PedidoDAO getPedidoDAO() {
		return new MySqlPedidoDAO();
	}

	@Override
	public ItemDAO getPratoDAO() {
		return new MySqlPratoDAO();
	}

	@Override
	public PromocaoDAO getPromocaoDAO() {
		return new MySqlPromocaoDAO();
	}

}