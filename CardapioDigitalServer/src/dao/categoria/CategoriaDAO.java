package dao.categoria;

import java.sql.SQLException;
import java.util.List;

import bean.Categoria;

public interface CategoriaDAO {
	public int incluir(Categoria c) throws SQLException;
    public boolean exluir(Categoria c) throws SQLException;
    public boolean alterar(Categoria c) throws SQLException;
    public Categoria consultarId(Categoria c) throws SQLException;
    public List<Categoria> consultarTitulo(Categoria c) throws SQLException;
    public List<Categoria> listar() throws SQLException;
    public void criarTabela() throws SQLException;
}
