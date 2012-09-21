package dao.tipo;

import java.sql.SQLException;
import java.util.List;

import bean.Tipo;

public interface TipoDAO {
	public int incluir(Tipo t) throws SQLException;
    public boolean exluir(Tipo t) throws SQLException;
    public boolean alterar(Tipo t) throws SQLException;
    public Tipo consultarId(Tipo t) throws SQLException;
    public List<Tipo> consultarTitulo(Tipo t) throws SQLException;
    public List<Tipo> listar() throws SQLException;
    public void criarTabela() throws SQLException;
}
