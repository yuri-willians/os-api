package com.yuri.os.resources.exceptions;

import java.io.Serializable;

public class StandandError implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Long timestamp;
    private Integer status;
    private String error;

    public StandandError() {
        super();
    }

    public StandandError(Long timestamp, Integer status, String error) {
        super();
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
