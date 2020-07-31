package com.example.smartcity.Model;

public class ApiResponse<T> {
    private T object;
    private boolean errorDetected;
    private Integer errorCode;

    public ApiResponse(T object) {
        // In that case it would mean something wrong happened and the number received is the error code
        if(object != null && object.getClass().equals(Integer.class)) {
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
