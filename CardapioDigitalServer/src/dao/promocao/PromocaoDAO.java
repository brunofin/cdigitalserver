package dao.promocao;

import java.sql.SQLException;
import java.util.List;

import bean.Promocao;

public interface PromocaoDAO {
	/**
	 * insere uma nova promoção,
	 * insere também sua foto, 
	 * os itens que estão em promoçao e
	 * a quantidade de cada item que está
	 * em promoção.
	 * 
	 * @param p promoção a ser inserida
	 * @return 1 ou mais se inseriu
	 * @throws SQLException
	 */
	public int incluir(Promocao p) throws SQLException;
	/**
	 * Exclui a promoção, sua ligação com os
	 * itens (tabela item_promocao) e a sua
	 * foto.
	 * 
	 * @param p promoção a ser excluída
	 * @return true se excluiu
	 * @throws SQLException
	 */
    public boolean excluir(Promocao p) throws SQLException;
    /**
     * Altera dados da promoção, sua foto
     * e seus itens (tabel item_promocao)
     * 
     * @param p promoçao a ser alterada
     * @return true se alterou
     * @throws SQLException
     */
    public boolean alterar(Promocao p) throws SQLException;
    public Promocao consultarId(Promocao p) throws SQLException;
    /**
     * Consulta pelo nome da promoção
     * 
     * @param p promoção com o nome ou parte
     * dele preenchido
     * @return
     * @throws SQLException
     */
    public List<Promocao> consultarTitulo(Promocao p) throws SQLException;
    public List<Promocao> listar() throws SQLException;
    public void criarTabela() throws SQLException;
}
