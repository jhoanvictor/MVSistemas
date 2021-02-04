package br.com.mv.controleFinanceiro.servicos;

import br.com.mv.controleFinanceiro.entidades.Endereco;
import br.com.mv.controleFinanceiro.persistencia.PkgEndereco;

public class EnderecoServicos {

	private PkgEndereco pkgEndereco = new PkgEndereco();

	public Endereco buscarEndereco(String cep) {
		return pkgEndereco.buscar(cep);
	}

	public Endereco cadastrar(Endereco endereco) {
		return pkgEndereco.cadastrarEndereco(endereco);
	}

}
