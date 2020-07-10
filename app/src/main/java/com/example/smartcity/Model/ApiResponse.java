package com.example.smartcity.Model;

import android.content.Intent;
import android.util.Log;

import com.example.smartcity.Utilitaries.StatusCode;

public class ApiResponse<T> {
    private T object;
    private boolean errorDetected;
    private Integer errorCode;

    public ApiResponse(T object) {
        if(object.getClass().equals(Integer.class)) {
            setErrorCode((Integer) object);
            setErrorDetected(true);
            setObject(null);
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
