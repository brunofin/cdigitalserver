package bean;

import java.util.Calendar;

public class Comentario {
	private int comentarioId;
	private String comentario;
	private Calendar data;
	
	public int getComentarioId() {
		return comentarioId;
	}
	public void setComentarioId(int comentarioId) {
		this.comentarioId = comentarioId;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	public Calendar getData() {
		return data;
	}
	
	public void setData(Calendar data) {
		this.data = data;
	}
}