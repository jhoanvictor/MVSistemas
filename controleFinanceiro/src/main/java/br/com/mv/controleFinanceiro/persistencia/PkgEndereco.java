package br.com.mv.controleFinanceiro.persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.mv.controleFinanceiro.entidades.Endereco;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class PkgEndereco {

	public Endereco buscar(String cep) {
		OracleCallableStatement params = null;
		Connection conn = new Conexao().getConnection();

		try {
			params = (OracleCallableStatement) conn.prepareCall("BEGIN DEVELOPER.PCK_ENDERECO.BUSCAR(:1, :2); END;");

			params.registerOutParameter(2, OracleTypes.CURSOR);

			params.setString(1, cep);

			params.execute();

			return preencheObjeto(params.getCursor(2));

		} catch (Exception e) {
			throw new RuntimeException("Erro ao salvar: " + e.getMessage());
		}
	}

	public Endereco cadastrarEndereco(Endereco endereco) {

		OracleCallableStatement params = null;
		Connection conn = new Conexao().getConnection();

		try {
			params = (OracleCallableStatement) conn
					.prepareCall("BEGIN DEVELOPER.PCK_ENDERECO.SALVAR(:1, :2, :3, :4, :5, :6, :7, :8); END;");

			params.registerOutParameter(8, OracleTypes.CURSOR);

			params.setString(1, endereco.getCep());
			params.setString(2, endereco.getLogradouro());
			params.setInt(3, endereco.getNumeroLogradouro());
			params.setString(4, endereco.getComplemento());
			params.setString(5, endereco.getBairro());
			params.setString(6, endereco.getCidade());
			params.setString(7, endereco.getUf());

			params.execute();

			return preencheObjeto(params.getCursor(8));

		} catch (Exception e) {
			throw new RuntimeException("Erro ao salvar: " + e.getMessage());
		}

	}

	private Endereco preencheObjeto(ResultSet rs) throws SQLException {
		Endereco endereco = new Endereco();
		while (rs.next()) {
			endereco.setCep(rs.getString("cep"));
			endereco.setBairro(rs.getString("bairro"));
			endereco.setComplemento(rs.getString("complemento"));
			endereco.setLogradouro(rs.getString("logradouro"));
			endereco.setNumeroLogradouro(rs.getInt("numero"));
			endereco.setUf(rs.getString("uf"));
		}
		return endereco;
	}

}
