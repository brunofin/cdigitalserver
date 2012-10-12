package dao.foto;

import java.sql.SQLException;
import java.util.List;

import bean.Foto;
import bean.Item;

public interface FotoDAO {
	/**
	 * Inclui foto na tabela foto
	 * e retorna o id dessa foto.
	 * Usar para incluir todas as fotos
	 * menos as de item. Para incluir
	 * fotos de item usar o 
	 * incluirFotosItem
	 * 
	 * @param f foto a ser inserida
	 * @return id int da foto inserida
	 * @throws SQLException
	 */
	public int incluir(Foto f) throws SQLException;
	/**
	 * Inclui fotos de um item
	 * @param i
	 * @return
	 * @throws SQLException
	 */
	public int incluirFotosItem (Item i) throws SQLException;
    public boolean excluir(Foto f) throws SQLException;
    public boolean excluirFotosItem(Item i) throws SQLException;
    /**
     * Altera local da foto.
     * 
     * @param f foto a ser a ser alterada
     * @return true se alterou a foto
     * @throws SQLException
     */
    public boolean alterar(Foto f) throws SQLException;
    public boolean alterarFotosItem(Item i)throws SQLException;
    public Foto consultarId(Foto f) throws SQLException;
    public List<Foto> consultarPorItemId(int itemId) throws SQLException;
    public List<Foto> listar() throws SQLException;
    public void criarTabela() throws SQLException;
}
