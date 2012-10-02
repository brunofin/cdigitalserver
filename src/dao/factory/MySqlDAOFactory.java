package dao.factory;

import java.sql.Connection;

import dao.categoria.CategoriaDAO;
import dao.categoria.MySqlCategoriaDAO;
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
import dao.item.MySqlItemDAO;
import dao.item.ItemDAO;
import dao.pedido.MySqlPedidoDAO;
import dao.pedido.PedidoDAO;
import dao.promocao.MySqlPromocaoDAO;
import dao.promocao.PromocaoDAO;
import dao.tipo.MySqlTipoDAO;
import dao.tipo.TipoDAO;

public class MySqlDAOFactory extends DAOFactory {
	    
    protected Connection getConnection(){
        return ConexaoSingleton.getConexao();
    }
    
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
	public ItemDAO getItemDAO() {
		return new MySqlItemDAO();
	}

	@Override
	public PromocaoDAO getPromocaoDAO() {
		return new MySqlPromocaoDAO();
	}

	@Override
	public TipoDAO getTipoDAO() {
		return new MySqlTipoDAO();
	}
	
	@Override
	public CategoriaDAO getCategoriaDAO() {
		return new MySqlCategoriaDAO();
	}

}