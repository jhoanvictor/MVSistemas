package br.com.mv.controleFinanceiro.servicos;

import br.com.mv.controleFinanceiro.entidades.Balanco;
import br.com.mv.controleFinanceiro.entidades.Cliente;
import br.com.mv.controleFinanceiro.entidades.Conta;
import br.com.mv.controleFinanceiro.persistencia.PkgBalanco;

public class BalancoServicos {

	private ContaServicos contaServicos = new ContaServicos();
	private PkgBalanco pkgBalanco = new PkgBalanco();

	public void cadastrar(Balanco balanco) {

		Conta conta = contaServicos.buscarConta(balanco.getConta().getNumero());

		if (conta.getNumero() != null) {
			balanco.setConta(conta);

			Cliente cliente = contaServicos.buscarPorCliente(conta.getCliente());

			if (cliente.getDocuemnto() != null) {

				if (balanco.getValor() > balanco.getConta().getSaldoAtual() && balanco.getDescricao().getCode() == 2) {
					throw new RuntimeException("O valor informado deve ser menor que o saldo atual");
				}

				if (contaServicos.operacao(conta, balanco.getValor(), balanco.getDescricao()).TRUE) {
					pkgBalanco.registrarMovimentacao(balanco);
				}

			} else {
				throw new RuntimeException("Cliente não encontrado para conta informada");
			}
		}

	}

}
