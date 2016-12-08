package br.com.souninja.rules;

import br.com.souninja.entity.ClienteRegimeTributario;

public enum RegimeTributario {

	SIMPLES_NACIONAL(1, "Simples Nacional"), LUCRO_PRESUMIDO(2, "Lucro Presumido");

	private RegimeTributario(Integer key, String regime) {
		this.regime = regime;
		this.key = key;
	}

	private String regime;
	private Integer key;

	public String getRegime() {
		return regime;
	}

	public Integer getKey() {
		return key;
	}

	public static RegimeTributario convertTo(ClienteRegimeTributario crt) {
		for(RegimeTributario regime : RegimeTributario.values()) {
			if(regime.getKey() == crt.getId()) {
				return regime;
			}
		}		
		throw new IllegalArgumentException("Não foi possível converter para o objeto informado.");
	}
	
	public Boolean equal(ClienteRegimeTributario crt) {
		return this.key == crt.getId();
	}
}
