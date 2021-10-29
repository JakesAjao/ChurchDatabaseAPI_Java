package com.jakesajao.Model;

public class ResponseData {
    private boolean status;

    private String Message;
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String type) {
        if (type=="Week"||type=="Category")
        Message = "The "+type+" selected is invalid.";
        else
            Message = type;
    }
    public ResponseData() {
    }
    public ResponseData(boolean status, String message) {
        this.status = status;
        Message = message;
    }
    private String ErrorMsg(String type){
        return "The "+type+" selected is invalid.";
    }
}
