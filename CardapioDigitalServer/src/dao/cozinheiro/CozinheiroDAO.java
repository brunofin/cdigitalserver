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
    public boolean excluir(Cozinheiro c) throws SQLException;
    public boolean alterar(Cozinheiro c) throws SQLException;
    public Cozinheiro consultarId(Cozinheiro c) throws SQLException;
    public List<Cozinheiro> consultarTitulo(Cozinheiro c) throws SQLException;
    public List<Cozinheiro> listar() throws SQLException;
    public void criarTabela() throws SQLException;
}
