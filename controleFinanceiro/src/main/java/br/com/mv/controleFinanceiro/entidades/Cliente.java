package br.com.mv.controleFinanceiro.entidades;

import java.io.Serializable;

import br.com.mv.controleFinanceiro.entidades.enums.TipoCliente;

public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	private String docuemnto;
	private String nome;
	private String sobrenome;
	private String razaoSocial;
	private String nomeFantasia;
	private String cep;
	private String telefone;
	private Integer tipoCliente;

	public Cliente() {
	}

	public Cliente(String docuemnto, String nome, String sobrenome, String razaoSocial, String nomeFantasia, String cep,
			String telefone) {
		super();
		this.docuemnto = docuemnto;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.razaoSocial = razaoSocial;
		this.nomeFantasia = nomeFantasia;
		this.cep = cep;
		this.telefone = telefone;
	}

	public String getDocuemnto() {
		return docuemnto;
	}

	public void setDocuemnto(String docuemnto) {
		this.docuemnto = docuemnto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public TipoCliente getTipoCliente() {
		return TipoCliente.valueOf(tipoCliente);
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		if (tipoCliente != null) {
			this.tipoCliente = tipoCliente.getCode();
		}
	}

}
