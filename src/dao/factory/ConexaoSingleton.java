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
			System.out.println("driver não encontrado " + e);
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
		if (con == null) {
			try {
				con = DriverManager.getConnection(jdbcURL, usuario, senha);
				System.out.println("conectado pelo ConexaoSingleton");
			} catch (SQLException e) {
				System.out.println("<ConexaoSingleton> Falha no driver: " + e.getMessage());
			}
		}
		return con;
	}
}
