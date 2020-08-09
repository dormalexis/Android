package com.example.smartcity.Model;

import com.example.smartcity.Utilitaries.StatusCode;

public class ApiResponse<T> {
    private T object;
    private boolean errorDetected;
    private Integer errorCode;
    private Integer code;

    public ApiResponse(T object) {
        // In that case it would mean something wrong happened and the number received is the error code

        if(object != null && object.getClass().equals(Integer.class)) {
            if((Integer) object == StatusCode.NOCONTENT.getErrorCode()) {
                setErrorDetected(false);
                setObject(null);
                setCode(StatusCode.NOCONTENT.getErrorCode());
            }

            else {
                setErrorCode((Integer) object);
                setErrorDetected(true);
                setObject(null);
            }

        }

        else {
            setErrorDetected(false);
            setObject(object);
            setErrorCode(null);
        }
    }

    public ApiResponse() {
        setErrorDetected(false);
        setErrorCode(null);
    }

    public Integer getCode() { return code; };
    public void setCode(Integer code) { this.code = code;}

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public boolean isErrorDetected() {
        return errorDetected;
    }

    public void setErrorDetected(boolean errorDetected) {
        this.errorDetected = errorDetected;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
