package dao.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSingleton {
	// TODO: fazer com que esse singleton pegue essas configurações pela classe servidor.Configuracao
	private static Connection con = null;
	private static final String jdbcURL = "jdbc:mysql://localhost:3306/tcc";
    private static final String usuario = "root"; 
    private static final String senha = "root";
    
    static {
    	try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
        	System.out.println("driver nÃ£o encontrado "+e);
        }
    }
    
	public static final Connection getConexao() {
		if(con == null){
			try {
	        	con = DriverManager.getConnection(jdbcURL,usuario,senha);
	            System.out.println("conectado pelo ConexaoSingleton");
	        }catch( SQLException e ){ 
	        	System.out.println("falha no driver "+e); 
	        }
		}
		return con;
	}
}
