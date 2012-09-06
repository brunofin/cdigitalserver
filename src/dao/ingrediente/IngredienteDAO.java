package dao.ingrediente;

import java.sql.SQLException;
import java.util.List;

import bean.Ingrediente;

public interface IngredienteDAO {
	public int incluir(Ingrediente i) throws SQLException;
    public boolean exluir(Ingrediente i) throws SQLException;
    public boolean alterar(Ingrediente i) throws SQLException;
    public Ingrediente consultarId(Ingrediente i) throws SQLException;
    public List<Ingrediente> consultarTitulo(Ingrediente i) throws SQLException;
    public List<Ingrediente> listar() throws SQLException;
    public void criarTabela() throws SQLException;
}
