package servidor.conexao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import servidor.comunicacao.Dispositivo;
import servidor.comunicacao.Pacote;

import bean.*;

/**
 * Representa uma conexão entre o Servidor e o Cliente
 * @author bruno
 *
 */
public class Conexao implements Runnable {
	private Socket cliente;
	private Servidor servidor;
	private Dispositivo dispositivo;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public Conexao(Socket cliente, Servidor servidor) {
		this.cliente = cliente;
		this.servidor = servidor;
	}
	
	@Override
	public void run() {
		try {
			iniciar();
		} catch(Exception e) {
			System.out.println("<Conexao>: Exceção iniciando! " + e.getMessage() );
		}
		
	}
	
	private void iniciar() throws IOException, ClassNotFoundException {
		in = new ObjectInputStream(cliente.getInputStream());
		out = new ObjectOutputStream(cliente.getOutputStream());
		
		
		dispositivo = (Dispositivo) in.readObject();
		
		System.out.println("<Conexao>: Conexão iniciada: " + dispositivo.getAndroid_id() + " : " + dispositivo.getMesa());
		
		while(true) {
			Pacote pacote = (Pacote) in.readObject();
			Object resposta = servidor.getCommunication().processa(this, pacote);
			
			if(resposta != null) {
				out.writeObject(resposta);
			}
		}
		//servidor.finalizarServidor();
	}

	public Socket getCliente() {
		return cliente;
	}

	public void setCliente(Socket cliente) {
		this.cliente = cliente;
	}

	public Servidor getServidor() {
		return servidor;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}
	
	public void fecharConexao() {
		try {
			in.close();
			out.close();
			cliente.close();
		} catch(IOException e) {
			System.out.println("<Conexao> Exceção finalizando! " + e.getMessage());
		}
		
	}

	public Dispositivo getDispositivo() {
		return dispositivo;
	}

}