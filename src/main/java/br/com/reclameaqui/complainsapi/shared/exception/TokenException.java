package br.com.reclameaqui.complainsapi.shared.exception;

public class TokenException extends RuntimeException {

    public TokenException(String mensagem) {
        super(mensagem);
    }

}
