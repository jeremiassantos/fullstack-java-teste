package br.com.souninja.rules;

import java.util.List;

import br.com.souninja.entity.ImpostoTipo;
import br.com.souninja.entity.NotaFiscal;

public enum ImpostoTipoRules {

	SIMPLES_NACIONAL(1, 10, 0.0), IRPJ(2, 20, 0.048), ISS(3, 10, 0.02), COFINS(4, 16, 0.03);

	private ImpostoTipoRules(Integer key, Integer diaVencimento, Double aliquota) {
		this.key = key;
		this.diaVencimento = diaVencimento;
		this.aliquota = aliquota;
	}

	private Integer key;
	private Integer diaVencimento;
	private Double aliquota;

	public Integer getKey() {
		return key;
	}

	public Double getAliquota() {
		return aliquota;
	}

	public Integer getDiaVencimento() {
		return diaVencimento;
	}

	public ImpostoTipo toImpostoTipo() {
		return new ImpostoTipo(key);
	}
	
	public Double aplicarAliquota(List<NotaFiscal> notaFiscal) {
		Double valorAliquota = new Double(0d);
		for(NotaFiscal n : notaFiscal) {
			valorAliquota += n.getValor() * aliquota;
		}
		return valorAliquota;
	}

}
