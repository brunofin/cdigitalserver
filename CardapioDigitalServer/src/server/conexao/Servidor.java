package server.conexao;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import server.CommunicationManager;

/**
 * Abre as portas para criar conexões e então insere uma representação da conexão em uma
 * lista. É basicamente o servidor
 * @author bruno
 * @see Conexao
 */
public class Servidor implements Runnable {
	private List<Conexao> conexoes;
	private boolean serverAlive;
	private CommunicationManager communication;
	
	public Servidor() {
		conexoes = new LinkedList<Conexao>();
		serverAlive = true;
	}
	@Override
	public void run() {
		try {
			iniciar();
		} catch(IOException e) {
			System.out.println("[server]<Servidor>: Exceção! " + e.getMessage());
		}
		
	}
	
	private void iniciar() throws IOException {
		ServerSocket server = new ServerSocket(4445);
		
		while(isServerAlive()) {
			System.out.println("[server]<Servidor> Esperando novos clientes...");
			Socket cliente = server.accept();
			
			System.out.println("[server]<Servidor> Nova conexão recebida!");
			Conexao c = new Conexao(cliente, this);
			
			new Thread(c).start();
			getConexoes().add(c);
		}
		System.out.println("[server]<Servidor>: Não aceitando novos clientes.");
		server.close();
	}
	
	public void finalizarServidor() {
		System.out.println("[server]<Servidor>: Finalizando servidor...");
		serverAlive = false;
		for(Conexao c : conexoes) {
			c.fecharConexao();
		}
		conexoes.clear();
		System.out.println("[server]<Servidor>: Servidor finalizado com sucesso!");
	}
	
	public List<Conexao> getConexoes() {
		return conexoes;
	}
	
	public void setConexoes(List<Conexao> conexoes) {
		this.conexoes = conexoes;
	}
	
	public boolean isServerAlive() {
		return serverAlive;
	}
	
	public CommunicationManager getCommunication() {
		return communication;
	}
}
