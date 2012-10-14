package dao.item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Categoria;
import bean.Comentario;
import bean.Item;
import bean.Tipo;
import dao.factory.MySqlDAOFactory;

public class MySqlItemDAO extends MySqlDAOFactory implements ItemDAO {
	public static final int TAMANHO_NOME = 38;
	public static final int TAMANHO_DESCRICAO = 58;
	public static final float PRECO_MAXIMO = 999999.99f; 
	@Override
	public boolean incluir(Item i) throws SQLException {//OK
		Connection con = getConnection();
		con.setAutoCommit(false);
        Statement stmt =  con.createStatement();
        //inclui item;
        stmt.executeUpdate("INSERT INTO item " +
        				"(nome, descricao, preco, id_categoria) " +
        				"VALUES ('"+i.getNome()+"','"
        				+i.getDescricao()+"',"+i.getPreco()+","
        				+i.getCategoria().getCategoriaId()+")",
        				Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stmt.getGeneratedKeys();
        int idRecemInserido = 0;
        //recupera ID do item recem inserido
        while (rs.next()){
        	idRecemInserido = rs.getInt(1);
        }
        stmt.close();
        i.setItemId(idRecemInserido);
        int incluiuFotos = 0, incluiuIngredientes = 0;
        if(i.getFoto()!=null && i.getFoto().size()!=0){
        	//inclui fotos do item na tabela foto
    		incluiuFotos = getFotoDAO().incluirFotosItem(i);
        }
        if(i.getIngredientes()!=null && i.getIngredientes().size()!=0){
    		//inclui ingredientes do item na tabela item_ingrediente
    		incluiuIngredientes = getItemIngredienteDAO().inserir(i);
        }
        if(idRecemInserido > 0 && incluiuFotos > 0 && incluiuIngredientes > 0){
        	con.commit();
        	//comita alterações se tudo foi inserido
        	con.setAutoCommit(true);
        	return true;
        }
        return false;
	}

	@Override
	public boolean exluir(Item i) throws SQLException {//TODO testar!
		//TODO excluir fotos, comentarios, linhas de item_ingrediente
		//talves excluir tambem os pedidos e as promoções
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		int excluiu = stmt.executeUpdate("DELETE FROM item WHERE id_item="+i.getItemId());
		//exclui fotos do item
		if(excluiu > 0){
			getFotoDAO().excluirFotosItem(i);
			getItemIngredienteDAO().excluir(i);
			getComentarioDAO().excluirTodosComentariosDeItemExcluido(i);
			//TODO excluirPedidos com esse item????
			//TODO excluirPromocoes com esse item???
			return true;
		}
		
		return false;
	}

	@Override
	public boolean alterar(Item i, List <Comentario> comentariosAtualizados) throws SQLException {//OK
		//alterar ingredientes, fotos e comentario
		Connection con = getConnection();
		PreparedStatement stmt = con.prepareStatement("UPDATE item SET " +
				"nome = ?, descricao = ?, preco = ?, id_categoria = ? " +
				"WHERE id_item="+i.getItemId());
		stmt.setString(1, i.getNome());  //nome
	    stmt.setString(2, i.getDescricao());  //descricao
	    stmt.setFloat(3, i.getPreco());//preco
	    stmt.setInt(4, i.getCategoria().getCategoriaId());//id_categoria
		stmt.execute();
		stmt.close();
		//altera fotos
		getFotoDAO().alterarFotosItem(i);
		//altera ingredientes tabela item_ingrediente
		getItemIngredienteDAO().alterarIngredientes(i);
		//altera comentarios
		getComentarioDAO().alterarComentariosItemEditado(i.getItemId(), comentariosAtualizados);
		return true;
	}

	@Override
	public Item consultarId(Item i) throws SQLException {//OK
		Item resultado = new Item();
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT i.id_item, i.nome, i.descricao, i.preco, " +
				"c.id_categoria, c.nome as nome_cat, c.descricao as desc_cat, t.tipo_id, " +
				"t.nome as nome_tipo FROM item AS i INNER JOIN categoria AS c INNER JOIN " +
				"tipo AS t ON i.id_categoria = c.id_categoria AND c.tipo_id = t.tipo_id " +
				"WHERE id_item="+i.getItemId());
		while(rs.next()){
			resultado.setItemId(rs.getInt("id_item"));
        	resultado.setNome(rs.getString("nome"));
        	resultado.setDescricao(rs.getString("descricao"));
        	resultado.setPreco(rs.getFloat("preco"));
        	resultado.setCategoria(new Categoria());
        	resultado.getCategoria().setCategoriaId(rs.getInt("id_categoria"));
        	resultado.getCategoria().setNome(rs.getString("nome_cat"));
        	resultado.getCategoria().setDescricao(rs.getString("desc_cat"));
        	resultado.getCategoria().setTipo(new Tipo());
        	resultado.getCategoria().getTipo().setTipoId(rs.getInt("tipo_id"));
        	resultado.getCategoria().getTipo().setNome(rs.getString("nome_tipo"));
        	//seta lista ingredientes
        	resultado.setIngredientes(getItemIngredienteDAO().consultarPorItemId(i.getItemId()));
        	//seta lista fotos
        	resultado.setFotos(getFotoDAO().consultarPorItemId(i.getItemId()));
		}
		return resultado;
	}

	@Override
	public List<Item> consultarTitulo(Item i) throws SQLException {//OK
		Item resultado;
		List <Item> itens = new ArrayList<Item>(); 
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT i.id_item, i.nome, i.descricao, i.preco, " +
				"c.id_categoria, c.nome as nome_cat, c.descricao as desc_cat, t.tipo_id, " +
				"t.nome as nome_tipo FROM item AS i INNER JOIN categoria AS c INNER JOIN " +
				"tipo AS t ON i.id_categoria = c.id_categoria AND c.tipo_id = t.tipo_id " +
				"WHERE i.nome LIKE ('%" +i.getNome()+ "%')");
		
		while(rs.next()){
			
			resultado = new Item();
			resultado.setItemId(rs.getInt("id_item"));
        	resultado.setNome(rs.getString("nome"));
        	resultado.setDescricao(rs.getString("descricao"));
        	resultado.setPreco(rs.getFloat("preco"));
        	resultado.setCategoria(new Categoria());
        	resultado.getCategoria().setCategoriaId(rs.getInt("id_categoria"));
        	resultado.getCategoria().setNome(rs.getString("nome_cat"));
        	resultado.getCategoria().setDescricao(rs.getString("desc_cat"));
        	resultado.getCategoria().setTipo(new Tipo());
        	resultado.getCategoria().getTipo().setTipoId(rs.getInt("tipo_id"));
        	resultado.getCategoria().getTipo().setNome(rs.getString("nome_tipo"));
        	//seta lista ingredientes
        	resultado.setIngredientes(getItemIngredienteDAO().consultarPorItemId(resultado.getItemId()));
        	//seta lista fotos
        	resultado.setFotos(getFotoDAO().consultarPorItemId(resultado.getItemId()));
        	itens.add(resultado);
		}
		stmt.close();
		return itens;
	}

	@Override
	public List<Item> listar() throws SQLException {//OK
		Item resultado;
		List <Item> itens = new ArrayList<Item>(); 
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT i.id_item, i.nome, i.descricao, i.preco, " +
				"c.id_categoria, c.nome as nome_cat, c.descricao as desc_cat, t.tipo_id, " +
				"t.nome as nome_tipo FROM item AS i INNER JOIN categoria AS c INNER JOIN " +
				"tipo AS t ON i.id_categoria = c.id_categoria AND c.tipo_id = t.tipo_id;");
		
		while(rs.next()){
			
			resultado = new Item();
			resultado.setItemId(rs.getInt("id_item"));
        	resultado.setNome(rs.getString("nome"));
        	resultado.setDescricao(rs.getString("descricao"));
        	resultado.setPreco(rs.getFloat("preco"));
        	resultado.setCategoria(new Categoria());
        	resultado.getCategoria().setCategoriaId(rs.getInt("id_categoria"));
        	resultado.getCategoria().setNome(rs.getString("nome_cat"));
        	resultado.getCategoria().setDescricao(rs.getString("desc_cat"));
        	resultado.getCategoria().setTipo(new Tipo());
        	resultado.getCategoria().getTipo().setTipoId(rs.getInt("tipo_id"));
        	resultado.getCategoria().getTipo().setNome(rs.getString("nome_tipo"));
        	//seta lista ingredientes
        	resultado.setIngredientes(getItemIngredienteDAO().consultarPorItemId(resultado.getItemId()));//TESTAR
        	//seta lista fotos
        	resultado.setFotos(getFotoDAO().consultarPorItemId(resultado.getItemId()));//TESTAR
        	itens.add(resultado);
        	
		}
		stmt.close();
		return itens;
	}

	@Override
	public void criarTabela() throws SQLException {//OK
		Connection con = getConnection();
		Statement stmt =  con.createStatement();
		stmt.execute("CREATE TABLE IF NOT EXISTS item (" +
                " id_item INTEGER (7) AUTO_INCREMENT NOT NULL," +
                " nome VARCHAR (40) NOT NULL," +
                " descricao VARCHAR (60) NOT NULL," +
                " preco DECIMAL (8,2) NOT NULL," +
                " id_categoria INTEGER (7) NOT NULL," +
                " PRIMARY KEY (id_item)," +
                " FOREIGN KEY (id_categoria) REFERENCES categoria (id_categoria))");
		stmt.close();
	}
}