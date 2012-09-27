package servidor.comunicacao;

import java.io.Serializable;

/**
 * Ver descrição em servidor.comunicacao.GerenciadorComunicacao
 * @see servidor.comunicacao.GerenciadorComunicacao
 *
 */
public class Pacote implements Serializable {
	private GerenciadorComunicacao.METODO metodo;
	private Object argumentos;
	
	public Pacote() {
		super();
	}
	
	public Pacote(GerenciadorComunicacao.METODO metodo, Object argumentos) {
		this();
		this.metodo = metodo;
		this.argumentos = argumentos;
	}
	
	public GerenciadorComunicacao.METODO getMetodo() {
		return metodo;
	}
	
	public void setMetodo(GerenciadorComunicacao.METODO metodo) {
		this.metodo = metodo;
	}
	
	public Object getArgumentos() {
		return argumentos;
	}
	
	public void setArgumentos(Object argumentos) {
		this.argumentos = argumentos;
	}
}
