package server.conexao;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * Abre as portas para criar conexões e então insere uma representação da conexão em uma
 * lista. É basicamente o servidor
 * @author bruno
 * @see Connection
 */
public class ConnectionManager implements Runnable {
	private List<Connection> conexoes;
	private boolean serverAlive;
	
	public ConnectionManager() {
		conexoes = new LinkedList<Connection>();
		serverAlive = true;
	}
	@Override
	public void run() {
		try {
			iniciar();
		} catch(IOException e) {
			System.out.println("[server]<ConnectionManager>: Exceção! " + e.getMessage());
		}
		
	}
	
	private void iniciar() throws IOException {
		ServerSocket server = new ServerSocket(4445);
		
		while(isServerAlive()) {
			System.out.println("[server]<ConnectionManager> Esperando novos clientes...");
			Socket cliente = server.accept();
			
			System.out.println("[server]<ConnectionManager> Nova conexão recebida!");
			Connection c = new Connection(cliente, this);
			
			new Thread(c).start();
			getConexoes().add(c);
		}
		System.out.println("[server]<ConnectionManager>: Não aceitando novos clientes.");
		server.close();
	}
	
	public void finalizarServidor() {
		System.out.println("[server]<ConnectionManager>: Finalizando servidor...");
		serverAlive = false;
		for(Connection c : conexoes) {
			c.fecharConexao();
		}
		conexoes.clear();
		System.out.println("[server]<ConnectionManager>: Servidor finalizado com sucesso!");
	}
	
	public List<Connection> getConexoes() {
		return conexoes;
	}
	
	public void setConexoes(List<Connection> conexoes) {
		this.conexoes = conexoes;
	}
	
	public boolean isServerAlive() {
		return serverAlive;
	}
}
