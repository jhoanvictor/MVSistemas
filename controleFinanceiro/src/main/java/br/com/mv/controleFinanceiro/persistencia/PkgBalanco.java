package br.com.mv.controleFinanceiro.persistencia;

import java.sql.Connection;

import br.com.mv.controleFinanceiro.entidades.Balanco;
import oracle.jdbc.OracleCallableStatement;

public class PkgBalanco {

	public Boolean registrarMovimentacao(Balanco balanco) {

		OracleCallableStatement params = null;
		Connection conn = new Conexao().getConnection();

		try {
			params = (OracleCallableStatement) conn
					.prepareCall("BEGIN DEVELOPER.PCK_BALANCO.REGISTRAR(:1, :2, :3); END;");

			params.setInt(1, balanco.getConta().getNumero());
			params.setDouble(2, balanco.getValor());
			params.setInt(3, balanco.getDescricao().getCode());

			return params.execute();

		} catch (Exception e) {
			throw new RuntimeException("Erro ao salvar: " + e.getMessage());
		}

	}

}
