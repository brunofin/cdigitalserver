package dao.factory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import servidor.Configuracao;

public class ConexaoSingleton {
	private static Connection con = null;
	private static final String jdbcURL; // localhost:3306
	private static final String usuario; // root
	private static final String senha; // root

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out
					.println("<ConexaoSingleton> Driver não encontrado: " + e);
		}

		Configuracao cfg = new Configuracao();

		try {
			cfg.ler();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("<ConexaoSingleton> Erro ao ler configurações: "
					+ e.getMessage());
		}

		usuario = cfg.getDbUsuario();
		senha = cfg.getDbSenha();
		jdbcURL = "jdbc:mysql://" + cfg.getDbIp() + ":" + cfg.getDbPorta()
				+ "/tcc";
	}

	public static final Connection getConexao() {
		try {
			if (con == null || con.isClosed())
				con = DriverManager.getConnection(jdbcURL, usuario, senha);
			System.out.println("<ConexaoSingleton> Conectado ao banco de dados.");
		} catch (SQLException e) {
			System.out.println("<ConexaoSingleton> Falha no driver: "
					+ e.getMessage());
		}
		return con;
	}
}
