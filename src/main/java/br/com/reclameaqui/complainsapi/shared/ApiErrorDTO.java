package br.com.reclameaqui.complainsapi.shared;

import java.io.Serializable;

public class ApiErrorDTO implements Serializable {

    private String campo;
    private String mensagem;

    public ApiErrorDTO() {
    }

    public ApiErrorDTO(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public ApiErrorDTO(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
