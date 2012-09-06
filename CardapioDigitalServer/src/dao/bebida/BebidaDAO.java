package dao.bebida;

import java.sql.SQLException;
import java.util.List;

import bean.Bebida;

public interface BebidaDAO {
	public int incluir(Bebida b) throws SQLException;
    public boolean exluir(Bebida b) throws SQLException;
    public boolean alterar(Bebida b) throws SQLException;
    public Bebida consultarId(Bebida b) throws SQLException;
    public List<Bebida> consultarTitulo(Bebida b) throws SQLException;
    public List<Bebida> listar() throws SQLException;
    public void criarTabela() throws SQLException;
}