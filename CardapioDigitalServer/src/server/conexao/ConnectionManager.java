package server.conexao;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class ConnectionManager implements Runnable {
	private List<Connection> conexoes;
	
	public ConnectionManager() {
		conexoes = new LinkedList<Connection>();
	}
	@Override
	public void run() {
		try {
			iniciar();
		} catch(IOException e) {
			
		}
		
	}
	
	private void iniciar() throws IOException {
		@SuppressWarnings("resource") //ver o próximo TODO e depois deletar esta linha.
		ServerSocket server = new ServerSocket(4445);
		
		System.out.println("Esperando clientes...");
		
		while(true) { //TODO: depois mudar o true pra uma opção na GUI que possa parar o servidor. 
			Socket cliente = server.accept();
			System.out.println("Servidor: Nova conexão recebida!");
			
			Connection c = new Connection(cliente, this);
			
			new Thread(c).start();
			getConexoes().add(c);
		}
		//server.close();
	}
	public List<Connection> getConexoes() {
		return conexoes;
	}
	public void setConexoes(List<Connection> conexoes) {
		this.conexoes = conexoes;
	}
}
