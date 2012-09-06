package dao.cliente;

import java.sql.SQLException;
import java.util.List;

import bean.Cliente;

public interface ClienteDAO {
	public int incluir(Cliente c) throws SQLException;
    public boolean exluir(Cliente c) throws SQLException;
    public boolean alterar(Cliente c) throws SQLException;
    public Cliente consultarId(Cliente c) throws SQLException;
    public List<Cliente> consultarTitulo(Cliente c) throws SQLException;
    public List<Cliente> listar() throws SQLException;
    public void criarTabela() throws SQLException;
}
