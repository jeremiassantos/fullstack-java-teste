package br.com.souninja.dto;

import br.com.souninja.rules.ImpostoTipoRules;

public class ImpostoLucroPresumidoDto {

	private ImpostoTipoRules ImpostoTipoRules;
	private Double valorTotal;

	public ImpostoLucroPresumidoDto(br.com.souninja.rules.ImpostoTipoRules impostoTipoRules, Double valorTotal) {
		super();
		ImpostoTipoRules = impostoTipoRules;
		this.valorTotal = valorTotal;
	}

	public ImpostoTipoRules getImpostoTipoRules() {
		return ImpostoTipoRules;
	}

	public void setImpostoTipoRules(ImpostoTipoRules impostoTipoRules) {
		ImpostoTipoRules = impostoTipoRules;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

}
