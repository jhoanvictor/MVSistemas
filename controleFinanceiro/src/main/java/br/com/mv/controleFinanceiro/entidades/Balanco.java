package br.com.mv.controleFinanceiro.entidades;

import java.io.Serializable;
import java.sql.Date;

import br.com.mv.controleFinanceiro.entidades.enums.TipoMovimentacao;

public class Balanco implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Double valor;
	private Integer descricao;
	private Conta conta;
	private Date data;

	public Balanco() {
	}

	public Balanco(Double valor, TipoMovimentacao descricao, Conta conta) {
		this.valor = valor;
		setDescricao(descricao);
		this.conta = conta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public TipoMovimentacao getDescricao() {
		return TipoMovimentacao.valueOf(descricao);
	}

	public void setDescricao(TipoMovimentacao descricao) {
		if (descricao != null) {
			this.descricao = descricao.getCode();
		}
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Balanco other = (Balanco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
