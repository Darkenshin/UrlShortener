package com.example.springboot.exception;

public class ErrorMessage {
    private String message;
    private String url;

    public ErrorMessage() {
    }

    public String getMessage() {
        return message;
    }

    public ErrorMessage(String message,String url) {
        this.message = message;
        this.url=url;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
