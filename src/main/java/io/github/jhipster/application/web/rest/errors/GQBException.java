package io.github.jhipster.application.web.rest.errors;

/**
 * Created by anil on 7/12/17.
 */

/**
 * Custom runtime exception
 */

public class GQBException extends RuntimeException {
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public GQBException(String message) {
        this(message, null);
    }

    public GQBException(String message, Object data) {
        super(message);
        this.data = data;
    }
}
