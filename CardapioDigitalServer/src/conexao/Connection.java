package conexao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection implements Runnable {
	private Socket cliente;
	private ConnectionManager servidor;
	
	public Connection(Socket cliente, ConnectionManager servidor) {
		this.cliente = cliente;
		this.servidor = servidor;
	}
	
	@Override
	public void run() {
		try {
			iniciar();
		} catch(Exception e) {
			
		}
		
	}
	
	private void iniciar() throws IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());
		ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());
		System.out.println("Conexão: Conexão iniciada!");
		
		while(true) {
			//TODO: fazer algo após conexão estabelecida (criar objeto RPC?)
		}
	}

	public Socket getCliente() {
		return cliente;
	}

	public void setCliente(Socket cliente) {
		this.cliente = cliente;
	}

	public ConnectionManager getServidor() {
		return servidor;
	}

	public void setServidor(ConnectionManager servidor) {
		this.servidor = servidor;
	}

}
