package br.com.mv.controleFinanceiro.entidades;

import java.io.Serializable;

public class Conta implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer numero;
	private Double saldoInicial;
	private Double saldoAnterior;
	private Double saldoAtual;
	private Cliente cliente;

	public Conta() {
	}

	public Conta(Integer numero, Double saldoInicial, Double saldoAnterior, Double saldoAtual, Cliente cliente) {
		this.numero = numero;
		this.saldoInicial = saldoInicial;
		this.saldoAnterior = saldoAnterior;
		this.saldoAtual = saldoAtual;
		this.cliente = cliente;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Double getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(Double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public Double getSaldoAnterior() {
		return saldoAnterior;
	}

	public void setSaldoAnterior(Double saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}

	public Double getSaldoAtual() {
		return saldoAtual;
	}

	public void setSaldoAtual(Double saldoAtual) {
		this.saldoAtual = saldoAtual;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		Conta other = (Conta) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

}
