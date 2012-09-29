package dao.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSingleton {
	private static Connection con = null;
	private static final String jdbcURL = "jdbc:mysql://localhost:3306/tcc";
    private static final String usuario = "root"; 
    private static final String senha = "root";
    
	public static final Connection getConexao() {
		if(con == null){
			try {
	        	con = DriverManager.getConnection(jdbcURL,usuario,senha);
	            System.out.println("conectado pelo ConexaoSingleton");
	        }catch( SQLException e ){ 
	        	System.out.println("falha no driver "+e); 
	        }
	        return con;
		}
		return con;
	}
}
