package dao.itemingrediente;
import java.sql.SQLException;
import java.util.List;
import bean.Ingrediente;
import bean.Item;

public interface ItemIngredienteDAO {
	/**
	 * Método para consultar ingredientes de um
	 * item
	 * 
	 * @param idItem, id do item
	 * @return lista de ingredientes do item
	 * @throws SQLException
	 */
	public List <Ingrediente> consultarPorItemId(int idItem) throws SQLException;
	/**
	 * Método para inserir os ingredientes de um
	 * novo item tabela de relacionamento entre item e 
	 * ingrediente, o atributo quantidade deve ser 
	 * preenchido.
	 *  
	 * @param i, item com seus ingredientes
	 * @return 1 ou mais se inserir
	 * @throws SQLException
	 */
	public int inserir(Item i) throws SQLException;
	/**
	 * Método para excluir lista de ingredientes de um item
	 * Deve ser chamado quando um Item for deletado.
	 * (não deleta os ingredientes da tabela ingrediente)
	 * 
	 * @param i, item com o id preenchido
	 * @return true se excluir lista de ingredientes
	 * @throws SQLException
	 */
	public boolean excluir(Item i) throws SQLException;
	/**
	 * Altera os ingredientes de um Item.
	 * 
	 * @param i, item com os ingredientes alterados
	 * @return true se alterar os ingredientes
	 */
	public boolean alterarIngredientes(Item i) throws SQLException;
	
	public void criarTabela() throws SQLException;
}
