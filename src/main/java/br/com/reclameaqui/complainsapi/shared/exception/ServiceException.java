package br.com.reclameaqui.complainsapi.shared.exception;

public class ServiceException extends RuntimeException {

    public ServiceException(String mensagem) {
        super(mensagem);
    }

}
