package br.com.souninja.rules;

import br.com.souninja.entity.AnexoTipo;
import br.com.souninja.entity.NotaFiscal;

public enum SimplesNacionalRules {

	COMERCIO(1, "Comércio", 0.06), 
	INDUSTRIA(2, "Indústria", 0.085), 
	PRESTACAO_DE_SERVICO(3, "Prestação de serviços", 0.11);

	private SimplesNacionalRules(Integer key, String name, Double value) {
		this.key = key;
		this.name = name;
		this.value = value;
	}

	private Integer key;
	private String name;
	private Double value;
	
	public Integer getKey() {
		return key;
	}
	public String getName() {
		return name;
	}
	public Double getValue() {
		return value;
	}

	public AnexoTipo getAnexoTipo() {
		return new AnexoTipo(key);
	}
	
	public static Double notaFiscalCalcule(NotaFiscal fiscal) {
		
		for(SimplesNacionalRules rule : SimplesNacionalRules.values()) {
			if(fiscal.getAnexoTipo().getId() == rule.getKey()) {
				return fiscal.getValor() * rule.getValue();
			}
		}
		
		throw new IllegalArgumentException("Não foi possível calcular o valor.");
	}
	
}
