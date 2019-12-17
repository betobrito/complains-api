package br.com.reclameaqui.complainsapi.shared;

import java.io.Serializable;

public class ApiErrorDTO implements Serializable {

    private String field;
    private String message;

    public ApiErrorDTO() {
    }

    public ApiErrorDTO(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public ApiErrorDTO(String message) {
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
