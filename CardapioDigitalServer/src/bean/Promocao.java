package bean;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Promocao {
	private int id;
	private Foto foto;
	private String nome;
	private String descricao;
	private Calendar validade;
	
	public Promocao() {
		this.foto = new Foto();
		this.validade = new GregorianCalendar();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Foto getFoto() {
		return foto;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Calendar getValidade() {
		return validade;
	}

	public void setValidade(Calendar validade) {
		this.validade = validade;
	}	
}