package bean;

public class Bebida {
	private int bebida_id;
	private int foto_id;
	private String nome;
	private String descricao;
	private float preco;
	
	public void setId(int id) {
		this.bebida_id = id;
	}
	
	public int getId() {
		return bebida_id;
	}
	
	public void setFotoId(int id) {
		this.foto_id = id;
	}
	
	public int getFotoId() {
		return foto_id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setPreco(float preco) {
		this.preco = preco;
	}
	
	public float getPreco() {
		return preco;
	}
}