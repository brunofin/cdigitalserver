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
	 * novo item
	 * tabela de relacionamento entre item e 
	 * ingrediente
	 * 
	 * @param listaIngredientes, lista de ingredientes
	 * @param idItem, id do item
	 * @return 1 se inseriu
	 * @throws SQLException
	 */
	public int inserir(List <Ingrediente> listaIngredientes, int idItem) throws SQLException;
	/**
	 * Método para excluir os ingredientes de um item
	 * Deve ser chamado quando um Item for deletado
	 * 
	 * @param listaIngredientes
	 * @param idItem
	 * @return
	 * @throws SQLException
	 */
	public boolean excluir(List <Ingrediente> listaIngredientes, int idItem) throws SQLException;
	/**
	 * Altera os ingredientes de um Item,
	 * @param i
	 * @return
	 */
	public boolean alterarIngredientes(Item i) throws SQLException;
	
	public void criarTabela() throws SQLException;
}
