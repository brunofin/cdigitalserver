package dao.itemfoto;

import java.sql.SQLException;
import java.util.List;

import bean.Foto;

public interface ItemFotoDAO {
	/**
	 * Consulta as fotos na tabela item_foto de um 
	 * determinado item e chama o FotoDAO para preencher
	 * os objetos foto.
	 * 
	 * @param itemId, id do item para pesquisa de fotos
	 * @return Lista de com objeto Foto
	 * @throws SQLException
	 */
	public List<Foto> consultarPorItemId(int itemId) throws SQLException;
	/**
	 * Método chamado ao excluir um item para que todas os
	 * seus registros de fotos sejam apagados das tabelas
	 * foto e item_foto
	 * 
	 * @param itemId, id do item que será excluído
	 * @return boolean, true se excluiu, false se não excluiu
	 * @throws SQLException
	 */
	public boolean excluirTodasAsFotosItem(int itemId) throws SQLException;
	/**
	 * Método chamado quando se precisa excluir uma ou mais
	 * fotos de um item.
	 * 
	 * @param itemId, id do item
	 * @param fotos, lista com a(s) foto(s) para exclusão
	 * @return true ou false
	 * @throws SQLException
	 */
	public boolean excluirFotosItem(int itemId, List <Foto> fotos) throws SQLException;
	/**
	 * Método chamado quando é cadatrado um novo Item,
	 * depois de as fotos serem inseridas na tabela fotos,
	 * deve-se chamar esse método para inserir o id das
	 * fotos e o id do novo Item.
	 * 
	 * @param listaNovasFotos, lista com objetos Foto preenchidos
	 * @param itemId, id do novo item
	 * @return int, 1 se inseriu
	 * @throws SQLException
	 */
	public int inserirNovasFotos(List<Foto> listaNovasFotos, int itemId) throws SQLException;
	/**
	 * Método para inserir uma nova foto para um item
	 * 
	 * @param fotoId, id da nova foto
	 * @param itemId, id do item
	 * @return 1 se inseriu
	 * @throws SQLException
	 */
	public int inserirNovaFoto(int fotoId, int itemId) throws SQLException;
}
