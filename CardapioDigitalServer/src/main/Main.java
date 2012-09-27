package main;

import gui.controle.CtrJanelaInicio;

import java.sql.SQLException;
import java.util.List;

import bean.Tipo;

import servidor.conexao.Servidor;

import dao.factory.DAOFactory;
import dao.factory.Database;
import dao.tipo.TipoDAO;

/* Lista geral de o que falta fazer
 * TODO: fazer algo com as Exception quando usa try/catch (invés de usar um bloco vazio).
 */

public class Main {
	private DAOFactory factory;
	private TipoDAO tipoDAO;

	public static void main(String[] args) {
		/*
		 * CtrJanelaInicio janelaInicio = new CtrJanelaInicio();
		 * janelaInicio.executar();
		 * 
		 * 
		 * Thread servidor = new Thread(new Servidor());
		 * 
		 * servidor.start();
		 */

		Main main = new Main();
		main.prepararTabelas();
	}

	/**
	 * Método chamado ao iniciar aplicação para criar tabelas e fazer os inserts
	 * necessários.
	 */
	private void prepararTabelas() {
		factory = DAOFactory.getDaoFactory(Database.MYSQL);
		tipoDAO = factory.getTipoDAO();
		try {
			tipoDAO.criarTabela();
		} catch (SQLException e) {
			System.out.println("erro ao criar tabela tipo " + e);
		}
		consultarEInserirPratoBebida(1, "Bebida");
		consultarEInserirPratoBebida(2, "Prato");
	}

	/**
	 * Consulta se existe os tipos prato e bebida na tabela tipo, caso não
	 * existam insere.
	 * 
	 * @param tipoId id do tipo
	 * @param nome nome do tipo
	 */
	private void consultarEInserirPratoBebida(int tipoId, String nome) {
		Tipo t = new Tipo();
		t.setTipoId(tipoId);
		t.setNome(nome);
		List<Tipo> tipos = null;
		try {
			tipos = tipoDAO.consultarTitulo(t);
		} catch (SQLException e) {
			System.out.println("Erro ao consultar tipos por título: " + e);
		}
		// caso nao tenha tipo com o nome pesquisado (Prato, Bebida) insere
		if (tipos.isEmpty()) {
			try {
				System.out
						.println(tipoDAO.incluir(t) == 1 ? "inseriu novo tipo"
								: "não inseriu novo tipo");
			} catch (SQLException e) {
				System.out.println("erro ao inserir prato. " + e);
			}
		}
	}
}