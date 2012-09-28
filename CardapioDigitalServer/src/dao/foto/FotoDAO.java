package dao.foto;

import java.sql.SQLException;
import java.util.List;

import bean.Foto;

public interface FotoDAO {
	public int incluir(Foto f) throws SQLException;
    public boolean excluir(Foto f) throws SQLException;
    public boolean alterar(Foto f) throws SQLException;
    public Foto consultarId(Foto f) throws SQLException;
    public List<Foto> consultarPorItemId(int itemId) throws SQLException;
    public List<Foto> listar() throws SQLException;
    public void criarTabela() throws SQLException;
}
