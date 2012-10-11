package dao.cozinheiro;

import java.sql.SQLException;
import java.util.List;

import bean.Cozinheiro;

public interface CozinheiroDAO {
	/**
	 * Inclui novo cozinheiro,
	 * também inclui seu endereco na tabela 
	 * endereco e sua foto na tabela foto.
	 * 
	 * @param c, cozinheiro a ser inserido
	 * @return 1 ou mais caso insira
	 * @throws SQLException
	 */
	public int incluir(Cozinheiro c) throws SQLException;
	/**
	 * Exclui cozinheiro, seu endereço da
	 * tabela endereco e sua foto da tabela
	 * foto
	 * 
	 * @param c, cozinheiro com seu id, o id
	 * de sua foto e o id de seu endereço
	 * @return true se excluiu
	 * @throws SQLException
	 */
    public boolean excluir(Cozinheiro c) throws SQLException;
    /**
     * Altera dados do cozinheiro, os dados
     * da sua foto e os dados do seu endereço
     * 
     * @param c, cozinheiro a ser alterado
     * @return true se alterou
     * @throws SQLException
     */
    public boolean alterar(Cozinheiro c) throws SQLException;
    /**
     * Consulta cozinheiro pelo seu id,
     * o cozinheiro vem com endereço e 
     * foto
     * 
     * @param c, cozinheiro apenas com o
     * id preenchido
     * @return cozinheiro com foto e endereço
     * @throws SQLException
     */
    public Cozinheiro consultarId(Cozinheiro c) throws SQLException;
    /**
     * Consulta cozinheiro pelo nome,
     * 
     * @param c, cozinheiro apenas com o nome
     * ou parte do nome preenchido
     * 
     * @return lista de cozinheiros com o nome
     * pesquisado
     * @throws SQLException
     */
    public List<Cozinheiro> consultarTitulo(Cozinheiro c) throws SQLException;
    /**
     * Lista todos os cozinheiros com seus
     * endereços e fotos
     * 
     * @return lista com todos os cozinheiros
     * cadastrados no sistema
     * @throws SQLException
     */
    public List<Cozinheiro> listar() throws SQLException;
    public void criarTabela() throws SQLException;
}
