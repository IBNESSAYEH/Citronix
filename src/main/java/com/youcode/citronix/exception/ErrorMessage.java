package com.youcode.citronix.exception;



import java.util.Date;

public class ErrorMessage {

    private Date timestamp;
    private String message;
    private int status;

    public ErrorMessage(Date timestamp, String message, int status) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.status = status;

    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



}
