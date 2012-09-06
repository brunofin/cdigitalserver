package dao.promocao;

import java.sql.SQLException;
import java.util.List;

import bean.Promocao;

public interface PromocaoDAO {
	public int incluir(Promocao p) throws SQLException;
    public boolean exluir(Promocao p) throws SQLException;
    public boolean alterar(Promocao p) throws SQLException;
    public Promocao consultarId(Promocao p) throws SQLException;
    public List<Promocao> consultarTitulo(Promocao p) throws SQLException;
    public List<Promocao> listar() throws SQLException;
    public void criarTabela() throws SQLException;
}
