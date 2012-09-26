package server;

/**
 * Ver descrição em server.CommunicationManager
 * @see server.CommunicationManager
 *
 */
public class Pacote {
	private CommunicationManager.METODO metodo;
	private Object argumentos;
	
	public CommunicationManager.METODO getMetodo() {
		return metodo;
	}
	
	public void setMetodo(CommunicationManager.METODO metodo) {
		this.metodo = metodo;
	}
	
	public Object getArgumentos() {
		return argumentos;
	}
	
	public void setArgumentos(Object argumentos) {
		this.argumentos = argumentos;
	}
}
