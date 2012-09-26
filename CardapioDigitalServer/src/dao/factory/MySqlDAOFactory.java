package dao.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
	private String driverClass = "com.mysql.jdbc.Driver";
    private String jdbcURL = "jdbc:mysql://localhost:3306/tcc";
    private String usuario = "root"; 
    private String senha = "root";
	
    protected MySqlDAOFactory(){
        try{
            Class.forName(driverClass);
        }catch(ClassNotFoundException e){
        	System.out.println("driver não encontrado "+e);
        }
    }
    
    protected Connection getConnection() throws SQLException {
        Connection con=null;
        try {
        	con =DriverManager.getConnection(jdbcURL,usuario,senha);
            System.out.println("conectado");
        }catch( SQLException e ){ 
        	System.out.println("falha no driver "+e); 
        }
        return con;
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

}