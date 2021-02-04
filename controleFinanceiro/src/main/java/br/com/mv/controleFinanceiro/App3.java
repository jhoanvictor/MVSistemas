package br.com.mv.controleFinanceiro;

import java.util.Random;

import br.com.mv.controleFinanceiro.entidades.Balanco;
import br.com.mv.controleFinanceiro.entidades.Cliente;
import br.com.mv.controleFinanceiro.entidades.Conta;
import br.com.mv.controleFinanceiro.entidades.Endereco;
import br.com.mv.controleFinanceiro.entidades.enums.TipoMovimentacao;
import br.com.mv.controleFinanceiro.servicos.BalancoServicos;
import br.com.mv.controleFinanceiro.servicos.ClienteServicos;
import br.com.mv.controleFinanceiro.servicos.ContaServicos;
import br.com.mv.controleFinanceiro.servicos.EnderecoServicos;

public class App3 {

	public static void main(String[] args) {

		Endereco endereco = new Endereco("74353549", "Rua RF 2", 01, "Quadra 2", "Residencial Flamingo", "Goiânia",
				"GO");
		(new EnderecoServicos()).cadastrar(endereco);

		Cliente c1 = new Cliente("64818320000136", "", "", "MV Sistemas", "MV", "74353549", "999647489");
		(new ClienteServicos()).cadastrarCliente(c1);

		Conta conta1 = new Conta((new Random().nextInt(9999999)), 10000.0, 15000.0, 12000.0,
				(new ClienteServicos().buscarPorCliente("01195033109")));

		(new ContaServicos()).cadastrar(conta1);
		conta1 = new ContaServicos().buscarConta(conta1.getNumero());

		Balanco balanco = new Balanco(5000.0, TipoMovimentacao.CRÉDITO, conta1);
		(new BalancoServicos()).cadastrar(balanco);

	}

}
