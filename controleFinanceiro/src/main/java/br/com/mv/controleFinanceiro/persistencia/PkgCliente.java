package br.com.mv.controleFinanceiro.persistencia;

import java.sql.Connection;
import java.sql.ResultSet;

import br.com.mv.controleFinanceiro.entidades.Cliente;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class PkgCliente {

	OracleCallableStatement params = null;
	Connection conn = new Conexao().getConnection();

	public ResultSet buscarPorCliente(String documento) {

		try {
			params = (OracleCallableStatement) conn
					.prepareCall("BEGIN DEVELOPER.PCK_CLIENTE.BUSCARCLIENTE(:1, :2); END;");

			params.registerOutParameter(2, OracleTypes.CURSOR);

			params.setString(1, documento);

			params.execute();

			return params.getCursor(2);

		} catch (Exception e) {
			throw new RuntimeException("Erro ao buscar: " + e.getMessage());
		}
	}

	public Boolean cadastrarCliente(Cliente cliente) {
		try {
			params = (OracleCallableStatement) conn
					.prepareCall("BEGIN DEVELOPER.PCK_CLIENTE.SALVARCLIENTE(:1, :2, :3, :4, :5, :6); END;");

			params.setString(1, cliente.getDocuemnto());
			params.setString(2,
					(cliente.getTipoCliente().getCode() == 1 ? cliente.getNome() : cliente.getRazaoSocial()));
			params.setString(3,
					(cliente.getTipoCliente().getCode() == 1 ? cliente.getSobrenome() : cliente.getNomeFantasia()));
			params.setString(4, cliente.getCep());
			params.setString(5, cliente.getTelefone());
			params.setInt(6, cliente.getTipoCliente().getCode());

			return params.execute();

		} catch (Exception e) {
			throw new RuntimeException("Erro ao salvar: " + e.getMessage());
		}
	}

}
