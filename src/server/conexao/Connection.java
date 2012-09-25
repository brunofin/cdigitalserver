package server.conexao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import server.Device;

import bean.*;

/**
 * Representa uma conexão entre o Servidor e o Cliente
 * @author bruno
 *
 */
public class Connection implements Runnable {
	private Socket cliente;
	private ConnectionManager servidor;
	private Device device;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public Connection(Socket cliente, ConnectionManager servidor) {
		this.cliente = cliente;
		this.servidor = servidor;
	}
	
	@Override
	public void run() {
		try {
			iniciar();
		} catch(Exception e) {
			System.out.println("[server]<Connection>: Exceção iniciando! " + e.getMessage() );
		}
		
	}
	
	private void iniciar() throws IOException, ClassNotFoundException {
		in = new ObjectInputStream(cliente.getInputStream());
		out = new ObjectOutputStream(cliente.getOutputStream());
		
		
		device = (Device) in.readObject();
		
		System.out.println("[server]<Connection>: Conexão iniciada: " + device.getAndroid_id() + " : " + device.getMesa());
		
		while(true) {
			Object comando = in.readObject();
			
			if(comando instanceof String) {
				String s = (String) comando;
				if(s.startsWith("shutdown")) {
					break;
				} else {
					System.out.println("[server]<Connection>: Mensagem recebida: " + s);
				}
			} else if(comando instanceof Pedido) {
				// mais alguma coisa
			} else {
				// continuar a lista de possiveis ações
			}
		}
		System.out.println("[server]<Connection>: Finalizando servidor...");
		servidor.finalizarServidor();
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
	
	public void fecharConexao() {
		try {
			in.close();
			out.close();
			cliente.close();
		} catch(IOException e) {
			System.out.println("[servidor]<Connection> Exceção finalizando! " + e.getMessage());
		}
		
	}

}
