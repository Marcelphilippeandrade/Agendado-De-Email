package br.com.marcelphilippe.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;

	private String mensagem;

	public BusinessException() {
		super();
	}

	public BusinessException(String mensagem) {

		super(mensagem);
		this.mensagem = mensagem;

	}

	public String getMensagem() {
		return mensagem;
	}
}
