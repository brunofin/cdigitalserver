package servidor.conexao;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import servidor.comunicacao.GerenciadorComunicacao;

/**
 * Abre as portas para criar conexões e então insere uma representação da conexão em uma
 * lista. É basicamente o servidor
 * @author bruno
 * @see Conexao
 */
public class Servidor implements Runnable {
	private List<Conexao> conexoes;
	private boolean serverAlive;
	private GerenciadorComunicacao communication;
	private static Servidor servidor;
	private long ativadoEm;
	
	public Servidor() {
		conexoes = new LinkedList<Conexao>();
		serverAlive = true;
		servidor = this;
		ativadoEm = System.currentTimeMillis();
	}
	
	@Override
	public void run() {
		try {
			iniciar();
		} catch(IOException e) {
			System.out.println("<Servidor>: Exceção! " + e.getMessage());
		}
		
	}
	
	private void iniciar() throws IOException {
		ServerSocket server = new ServerSocket(4445);
		
		
		while(isServerAlive()) {
			System.out.println("<Servidor> Esperando novos clientes...");
			Socket cliente = server.accept();
			
			System.out.println("<Servidor> Nova conexão recebida!");
			Conexao c = new Conexao(cliente, this);
			
			new Thread(c).start();
			getConexoes().add(c);
		}
		System.out.println("<Servidor>: Não aceitando novos clientes.");
		server.close();
		server = null;
	}
	
	public void finalizarServidor() {
		System.out.println("<Servidor>: Finalizando servidor...");
		serverAlive = false;
		for(Conexao c : conexoes) {
			c.fecharConexao();
		}
		conexoes.clear();
		System.out.println("<Servidor>: Servidor finalizado com sucesso!");
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
	
	public GerenciadorComunicacao getCommunication() {
		return communication;
	}
	
	public static Servidor getServidor() {
		return servidor;
	}
	
	public long getAtivadoEm() {
		return ativadoEm;
	}
	
}
