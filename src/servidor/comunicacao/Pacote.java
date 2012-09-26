package servidor.comunicacao;

/**
 * Ver descrição em servidor.comunicacao.GerenciadorComunicacao
 * @see servidor.comunicacao.GerenciadorComunicacao
 *
 */
public class Pacote {
	private GerenciadorComunicacao.METODO metodo;
	private Object argumentos;
	
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
