package dao.endereco;

import java.sql.SQLException;
import java.util.List;

import bean.Endereco;

public interface EnderecoDAO {
	public int incluir(Endereco e) throws SQLException;
    public boolean exluir(Endereco e) throws SQLException;
    public boolean alterar(Endereco e) throws SQLException;
    public Endereco consultarId(Endereco e) throws SQLException;
    public List<Endereco> consultarTitulo(Endereco e) throws SQLException;
    public List<Endereco> listar() throws SQLException;
    public void criarTabela() throws SQLException;
}
