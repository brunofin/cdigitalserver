package dao.comentario;

import java.sql.SQLException;
import java.util.List;

import bean.Comentario;

public interface ComentarioDAO {
	public int incluir(Comentario c) throws SQLException;
    public boolean exluir(Comentario c) throws SQLException;
    public boolean alterar(Comentario c) throws SQLException;
    public Comentario consultarId(Comentario c) throws SQLException;
    public List<Comentario> consultarTitulo(Comentario c) throws SQLException;
    public List<Comentario> listar() throws SQLException;
    public void criarTabela() throws SQLException;
}
