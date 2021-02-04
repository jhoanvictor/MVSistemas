package br.com.mv.controleFinanceiro.servicos;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.mv.controleFinanceiro.entidades.Cliente;
import br.com.mv.controleFinanceiro.entidades.Endereco;
import br.com.mv.controleFinanceiro.entidades.enums.TipoCliente;
import br.com.mv.controleFinanceiro.persistencia.PkgCliente;

public class ClienteServicos {

	private PkgCliente pkgCliente = new PkgCliente();

	private Endereco endereco;

	public Cliente buscarPorCliente(String documento) {
		ResultSet rs = pkgCliente.buscarPorCliente(documento);
		try {
			Cliente cliente = new Cliente();
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
	
	public void cadastrarCliente(Cliente cliente) {

		endereco = (new EnderecoServicos()).buscarEndereco(cliente.getCep());

		if (cliente.getDocuemnto().length() < 11 || (cliente.getDocuemnto().length() > 11 && cliente.getDocuemnto().length() < 14) || cliente.getDocuemnto().length() > 14) {
			throw new RuntimeException("Informe um CPF ou um CNPJ para cadastrar!");
		}

		if (cliente.getCep().length() != 8) {
			throw new RuntimeException("Informe um CEP com 8 números");
		}

		if (endereco.getCep() == null) {
			throw new RuntimeException(
					"O CEP informado não está cadastrado na base de dados, insira um CEP cadastrado ou cadastre um Endereco!");
		}

		if (!cliente.getTelefone().isEmpty()
				&& (cliente.getTelefone().length() < 8 || cliente.getTelefone().length() > 9)) {
			throw new RuntimeException("Informe um número de telefone entre 8 e 9 dígitos");
		}

		if (cliente.getDocuemnto().length() == 11) {
			cliente.setTipoCliente(TipoCliente.PESSOA_FISICA);
		}

		if (cliente.getDocuemnto().length() == 14) {
			cliente.setTipoCliente(TipoCliente.PESSOA_JURIDICA);
		}

		if (pkgCliente.cadastrarCliente(cliente).TRUE) {
			System.out.println("Cadastro com sucesso!");
		}

	}

}
