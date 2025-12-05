package com.bci.evaluation.commons.exception;


import java.sql.Timestamp;

public class ExceptionResponse {

    private String detail;
    private int codigo;
    private Timestamp timestamp;

    public ExceptionResponse(String detail, int codigo, Timestamp timestamp) {
        this.detail = detail;
        this.codigo = codigo;
        this.timestamp = timestamp;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
