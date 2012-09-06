package dao.prato;

import java.sql.SQLException;
import java.util.List;

import bean.Prato;

public interface PratoDAO {
	public int incluir(Prato p) throws SQLException;
    public boolean exluir(Prato p) throws SQLException;
    public boolean alterar(Prato p) throws SQLException;
    public Prato consultarId(Prato p) throws SQLException;
    public List<Prato> consultarTitulo(Prato p) throws SQLException;
    public List<Prato> listar() throws SQLException;
    public void criarTabela() throws SQLException;
}
