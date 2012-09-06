package bean;

import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Pessoa {
	private int id;
	private String nome;
	private String email;
	private String rg;
	private String cpf;
	private Calendar dataNascimento;
	private Endereco endereco;
	
	public Pessoa() {
		this.dataNascimento = new GregorianCalendar();
		this.endereco = new Endereco();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public void setDiaNascimento(int dia) {
		dataNascimento.set(Calendar.DAY_OF_MONTH, dia);
	}
	
	public int getDiaNascimento() {
		return getDataNascimento().get(Calendar.DAY_OF_MONTH);
	}
	
	public void setMesNascimento(int mes) {
		dataNascimento.set(Calendar.MONTH, mes);
	}
	
	public int getMesNascimento() {
		return getDataNascimento().get(Calendar.MONTH);
	}
	
	public void setAnoNascimento(int ano) {
		dataNascimento.set(Calendar.YEAR, ano);
	}
	
	public int getAnoNascimento() {
		return getDataNascimento().get(Calendar.YEAR);
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}