package model;


/**
 * Created by anil on 5/10/18.
 */
public class GQBResponseObj extends ModelBase {
    private Object result;
    private String message;
    private Boolean responseStatus;
    private Object page;

    public GQBResponseObj() {
    }

    private GQBResponseObj(GQBResponseObjBuilder gqbResponseObjBuilder) {
        this.result = gqbResponseObjBuilder.result;
        this.message = gqbResponseObjBuilder.message;
        this.responseStatus = gqbResponseObjBuilder.responseStatus;
        this.page = gqbResponseObjBuilder.page;
    }

    public static class GQBResponseObjBuilder {
        private Object result;
        private String message;
        private Boolean responseStatus = true;
        private Object page;

        public GQBResponseObjBuilder() {
        }

        public GQBResponseObjBuilder result(Object result) {
            this.result = result;
            return this;
        }

        public GQBResponseObjBuilder message(String message) {
            this.message = message;
            return this;
        }

        public GQBResponseObjBuilder responseStatus(Boolean responseStatus) {
            this.responseStatus = responseStatus;
            return this;
        }

        public GQBResponseObjBuilder page(Object page) {
            this.page = page;
            return this;
        }

        public GQBResponseObj build() {
            return new GQBResponseObj(this);
        }

    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Boolean responseStatus) {
        this.responseStatus = responseStatus;
    }

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }
}
