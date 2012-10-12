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
	 * *obs: Esse método não inclui novos ingredientes
	 * na tabela de ingredientes, os mesmos devem ser 
	 * cadastrados antes de se cadastrar um novo item. 
	 * 
	 * @param i item com lista de fotos e lista de 
	 * ingredientes 
	 * @return true se inseriu tudo.
	 * @throws SQLException
	 */
	public boolean incluir(Item i) throws SQLException;
	/**
	 * Eclui item, suas fotos, suas ligações
	 * com ingredientes na tabela 
	 * item_ingrediente e seus comentários.
	 * *obs: não exclui os ingredientes da tabela
	 * ingrediente.
	 * 
	 * @param i, item a ser excluído com o seu id
	 * @return true se excluiu
	 * @throws SQLException
	 */
    public boolean exluir(Item i) throws SQLException;
    /**
     * Método para alterar um item.
     * altera também suas fotos, ingredientes e 
     * comentários
     * 
     * @param i item a ser editado
     * @param comentariosAtualizados lista com 
     * comentários atualizados
     * @return true se atualizou o item.
     * @throws SQLException
     */
    public boolean alterar(Item i, List <Comentario> comentariosAtualizados) throws SQLException;
    public Item consultarId(Item i) throws SQLException;
    /**
     * Consulta pelo nome do item, o item 
     * vem com todos os seus dados
     * @param i, item apenas com o nome ou
     * parte dele preenchido.
     * 
     * @return lista com itens que tenham
     * o nome pesquisado
     * @throws SQLException
     */
    public List<Item> consultarTitulo(Item i) throws SQLException;
    public List<Item> listar() throws SQLException;
    public void criarTabela() throws SQLException;
}
