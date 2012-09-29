package dao.cliente;

import java.sql.SQLException;
import java.util.List;

import bean.Cliente;

public interface ClienteDAO {
	/**
	 * Método para incluir novo Cliente,
	 * inclui também seu endereço.
	 * 
	 * @param c, novo Cliente a ser inserido
	 * @return 1 se inserir
	 * @throws SQLException
	 */
	public int incluir(Cliente c) throws SQLException;
	/**
	 * Método para excluir um Cliente,
	 * exclui também seu endereço da tabela endereco
	 * 
	 * @param c, cliente a ser removido
	 * @return true se removeu cliente
	 * @throws SQLException
	 */
    public boolean excluir(Cliente c) throws SQLException;
    /**
     * Método para editar um cliente,
     * edita também seu endereço na tabela 
     * endereco
     * 
     * @param c, cliente a ser editado
     * @return true se editou
     * @throws SQLException
     */
    public boolean alterar(Cliente c) throws SQLException;
    /**
     * Método para consultar cliente pelo id.
     * consulta também seu endereço e manda ele 
     * completo.
     * 
     * @param c, cliente com id para consulta
     * @return
     * @throws SQLException
     */
    public Cliente consultarId(Cliente c) throws SQLException;
    /**
     * Método para consultar pelo nome de um 
     * Cliente, os clientes vão com o endereço 
     * completo
     * 
     * @param c, cliente com o nome ou parte do nome
     * para pesquisa
     * @return lista de clientes com o nome pesquisado
     * @throws SQLException
     */
    public List<Cliente> consultarTitulo(Cliente c) throws SQLException;
    /**
     * Método para listar todos os clientes,
     * os clientes são retornados com seus 
     * endereços completos
     * 
     * @return lista de todos os clientes
     * @throws SQLException
     */
    public List<Cliente> listar() throws SQLException;
    /**
     * Método para criar tabela cliente
     * 
     * @throws SQLException
     */
    public void criarTabela() throws SQLException;
}
