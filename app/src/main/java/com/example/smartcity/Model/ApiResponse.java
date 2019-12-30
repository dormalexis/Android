package com.example.smartcity.Model;

import com.example.smartcity.Utilitaries.ApiResponseErrorCode;

public class ApiResponse<T> {
    private T object;
    private boolean errorDetected;
    private ApiResponseErrorCode errorCode;

    public ApiResponse(T object)
    {
        setErrorDetected(false);
        setObject(object);
        setErrorCode(null);
    }
    public ApiResponse(ApiResponseErrorCode errorCode)
    {
        setErrorCode(errorCode);
        setErrorDetected(true);
        setObject(null);
    }
    public ApiResponse()
    {
        setErrorDetected(false);
        setErrorCode(null);
        setObject(null);
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

    public ApiResponseErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ApiResponseErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
