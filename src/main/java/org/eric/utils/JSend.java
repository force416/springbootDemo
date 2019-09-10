package org.eric.utils;

public class JSend {
    private String status;
    private Object data;
    private String message;

    public static JSend success(Object data) {
        return new JSend("success", data, "");
    }

    public static JSend fail(String message) {
        return new JSend("fail", null, message);
    }

    public static JSend error(String message) {
        return new JSend("error", null, message);
    }

    public String getStatus() {
        return status;
    }

    public JSend(String status, Object data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
