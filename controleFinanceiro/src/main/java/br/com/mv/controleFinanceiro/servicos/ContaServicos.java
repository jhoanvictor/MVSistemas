package br.com.mv.controleFinanceiro.servicos;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.mv.controleFinanceiro.entidades.Cliente;
import br.com.mv.controleFinanceiro.entidades.Conta;
import br.com.mv.controleFinanceiro.entidades.enums.TipoCliente;
import br.com.mv.controleFinanceiro.entidades.enums.TipoMovimentacao;
import br.com.mv.controleFinanceiro.persistencia.PkgConta;

public class ContaServicos {

	private PkgConta pkgConta = new PkgConta();

	public Conta buscarConta(Integer numeroConta) {
		try {
			return preencheObjeto(pkgConta.buscar(numeroConta));
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public Cliente buscarPorCliente(Cliente cliente) {
		ResultSet rs = pkgConta.buscarPorCliente(cliente);
		try {
			while (rs.next()) {
				cliente.setDocuemnto(rs.getString("documento"));
				cliente.setTipoCliente(TipoCliente.valueOf(rs.getInt("tipoCliente")));
				cliente.setCep(rs.getString("cep"));
				cliente.setTelefone(rs.getString("telefone"));

				if (cliente.getTipoCliente().getCode() == 1) {
					cliente.setNome(rs.getString("nomeRazao"));
					cliente.setSobrenome(rs.getString("sobrenomeFantasia"));
				} else {
					cliente.setRazaoSocial(rs.getString("nomeRazao"));
					cliente.setNomeFantasia(rs.getString("sobrenomeFantasia"));
				}
			}

			return cliente;
		} catch (SQLException e) {
			throw new RuntimeException("Erro: " + e.getMessage());
		}
	}

	public void cadastrar(Conta conta) {

		pkgConta.cadastrarConta(conta);

	}

	public Boolean operacao(Conta conta, Double valor, TipoMovimentacao movimentacao) {
		return pkgConta.operacao(conta, valor, movimentacao);
	}

	private Conta preencheObjeto(ResultSet rs) throws SQLException {
		Conta conta = new Conta();
		while (rs.next()) {
			conta.setNumero(rs.getInt("numero"));
			conta.setSaldoAnterior(rs.getDouble("saldo_anterior"));
			conta.setSaldoAtual(rs.getDouble("saldo_atual"));
			conta.setSaldoInicial(rs.getDouble("saldo_inicial"));
			conta.setCliente((new ClienteServicos()).buscarPorCliente(rs.getString("cliente")));
		}
		return conta;
	}

}
