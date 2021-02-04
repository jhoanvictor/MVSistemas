package br.com.mv.controleFinanceiro.entidades.enums;

public enum TipoMovimentacao {
	
	CRÉDITO(1), DÉBITO(2);

	private int code;

	private TipoMovimentacao(int code) {
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}

	public static TipoMovimentacao valueOf(int code) {
		for (TipoMovimentacao value : TipoMovimentacao.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid TipoMovimentacao code");
	}
	
}
