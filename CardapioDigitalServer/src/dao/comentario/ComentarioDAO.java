package dao.comentario;

import java.sql.SQLException;
import java.util.List;

import bean.Comentario;
import bean.Item;

public interface ComentarioDAO {
	public int incluir(Comentario c) throws SQLException;
    public boolean excluir(Comentario c) throws SQLException;
    /**
     * Altera somente o atributo comentário.
     * 
     * @param c comentario a ser alterado
     * @return
     * @throws SQLException
     */
    public boolean alterar(Comentario c) throws SQLException;
    /**
     * Altera comentarios de um item editado.
     * Deve ser chamado ao alterar um item.
     * 
     * @param idItem id do item alterado 
     * @param comentariosAtualizados lista de comentarios
     * atualizada
     * @return
     * @throws SQLException
     */
    public boolean alterarComentariosItemEditado(int idItem, List <Comentario> comentariosAtualizados) throws SQLException;
    /**
     * Consulta comentarios de um Item,
     * para apresenta-los no cardápio
     * os comentários da lista só contem:
     * id do comentário, timeInMillis,
     * o texto do comentario e o nome do
     * Cliente que fez o comentário.
     * 
     * @param c comentário apenas com
     * id do Item preenchido.
     * @return lista de comentários de um
     * Item
     * @throws SQLException
     */
    public List<Comentario> consultarComentariosItem(Comentario c) throws SQLException;
    /**
     * Consulta todos os comentários de um 
     * cliente, os comentários vem com data,
     * o texto do comentário, e o nome do item 
     * que foi comentado
     * 
     * @param c comentário com apenas o 
     * id do cliente preenchido
     * @return lista de comentários de um cliente
     * @throws SQLException
     */
    public List<Comentario> consultarComentariosCliente(Comentario c) throws SQLException;
    /**
     * Exclui todos os comentário de um cliente
     * que será excluído
     * 
     * @param c comentario com apenas o
     * id do cliente que será excluido
     * @return true se excluiu os comentários
     * @throws SQLException
     */
    public boolean excluirComentariosClienteExcluido(Comentario c)throws SQLException;
    /**
     * Exclui todos os comentarios de um 
     * item.
     * Deve ser chamado ao excluir o item,
     * para que todos os seus comentários sejam 
     * excluídos
     * 
     * @param i item que foi excluído
     * @return
     * @throws SQLException
     */
    public boolean excluirTodosComentariosDeItemExcluido(Item i)throws SQLException;
    /**
     * Retorna uma lista com todos os comentários
     * do sistema, vem preenchidos apenas o id do,
     * comentário, a data e o texto do comentário.
     *  
     * @return lista de todos os comentários.
     * @throws SQLException
     */
    public List<Comentario> listar() throws SQLException;
    public void criarTabela() throws SQLException;
}
