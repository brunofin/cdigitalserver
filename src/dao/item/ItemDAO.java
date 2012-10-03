package dao.item;

import java.sql.SQLException;
import java.util.List;

import bean.Comentario;
import bean.Item;

public interface ItemDAO {
	/**
	 * Método para incluir um novo item.
	 * Inclui também as fotos na tabela foto
	 * e os ingredientes na tabela item_ingrediente.
	 * 
	 * @param i, item com lista de fotos e lista de 
	 * ingredientes 
	 * @return true se inseriu tudo.
	 * @throws SQLException
	 */
	public boolean incluir(Item i) throws SQLException;
    public boolean exluir(Item i) throws SQLException;
    /**
     * Método para alterar um item.
     * altera também suas fotos, ingredientes e 
     * comentários
     * 
     * @param i, item a ser editado
     * @param comentariosAtualizados, lista com 
     * comentários atualizados
     * @return true se atualizou o item.
     * @throws SQLException
     */
    public boolean alterar(Item i, List <Comentario> comentariosAtualizados) throws SQLException;
    public Item consultarId(Item i) throws SQLException;
    public List<Item> consultarTitulo(Item i) throws SQLException;
    public List<Item> listar() throws SQLException;
    public void criarTabela() throws SQLException;
}
