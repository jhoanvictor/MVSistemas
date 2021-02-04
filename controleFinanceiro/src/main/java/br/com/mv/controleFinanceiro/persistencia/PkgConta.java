package br.com.mv.controleFinanceiro.persistencia;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;

import br.com.mv.controleFinanceiro.entidades.Cliente;
import br.com.mv.controleFinanceiro.entidades.Conta;
import br.com.mv.controleFinanceiro.entidades.enums.TipoMovimentacao;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class PkgConta {

	OracleCallableStatement params = null;
	Connection conn = new Conexao().getConnection();

	public ResultSet buscar(Integer numeroConta) {

		try {
			params = (OracleCallableStatement) conn.prepareCall("BEGIN DEVELOPER.PCK_CONTA.BUSCARCONTA(:1, :2); END;");

			params.registerOutParameter(2, OracleTypes.CURSOR);

			params.setInt(1, numeroConta);

			params.execute();

			return params.getCursor(2);

		} catch (Exception e) {
			throw new RuntimeException("Erro ao salvar: " + e.getMessage());
		}
	}

	public ResultSet buscarPorCliente(Cliente cliente) {

		try {
			params = (OracleCallableStatement) conn
					.prepareCall("BEGIN DEVELOPER.PCK_CONTA.BUSCARCLIENTE(:1, :2); END;");

			params.registerOutParameter(2, OracleTypes.CURSOR);

			params.setString(1, cliente.getDocuemnto());

			params.execute();

			return params.getCursor(2);

		} catch (Exception e) {
			throw new RuntimeException("Erro ao buscar: " + e.getMessage());
		}
	}

	public Boolean cadastrarConta(Conta conta) {

		try {
			params = (OracleCallableStatement) conn
					.prepareCall("BEGIN DEVELOPER.PCK_CONTA.SALVAR(:1, :2, :3, :4, :5); END;");

			params.setInt(1, conta.getNumero());
			params.setBigDecimal(2, new BigDecimal(conta.getSaldoInicial()));
			params.setBigDecimal(3, new BigDecimal(conta.getSaldoAtual()));
			params.setBigDecimal(4, new BigDecimal(conta.getSaldoAnterior()));
			params.setString(5, conta.getCliente().getDocuemnto());

			return params.execute();

		} catch (Exception e) {
			throw new RuntimeException("Erro ao salvar: " + e.getMessage());
		}

	}

	public Boolean operacao(Conta conta, Double valor, TipoMovimentacao movimentacao) {

		try {
			params = (OracleCallableStatement) conn.prepareCall("BEGIN DEVELOPER.PCK_CONTA.OPERACAO(:1, :2, :3); END;");

			params.setInt(1, conta.getNumero());
			params.setBigDecimal(2, new BigDecimal(valor));
			params.setInt(3, movimentacao.getCode());

			return params.execute();

		} catch (Exception e) {
			throw new RuntimeException("Erro ao salvar: " + e.getMessage());
		}

	}

}
