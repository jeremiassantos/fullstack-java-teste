package br.com.souninja.messages;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonAutoDetect
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ValidationMessage {

	USER_NOT_FOUND("O cnpj informado não corresponde a nenhum usuário."),
	CNPJ_INVALIDO("CNPJ é inválido ou está em uso !"),
	EMAIL_INVALIDO("E-mail é inválido ou está em uso !"),
	CAMPO_OBRIGATORIO("Verifique os campos obrgatórios!");

	private ValidationMessage(String message) {
		this.message = message;
	}

	private String message;

	public String getMessage() {
		return message;
	}

}
