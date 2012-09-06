package dao.cozinheiro;

import java.sql.SQLException;
import java.util.List;

import bean.Cozinheiro;

public interface CozinheiroDAO {
	public int incluir(Cozinheiro c) throws SQLException;
    public boolean exluir(Cozinheiro c) throws SQLException;
    public boolean alterar(Cozinheiro c) throws SQLException;
    public Cozinheiro consultarId(Cozinheiro c) throws SQLException;
    public List<Cozinheiro> consultarTitulo(Cozinheiro c) throws SQLException;
    public List<Cozinheiro> listar() throws SQLException;
    public void criarTabela() throws SQLException;
}
