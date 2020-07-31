package com.example.smartcity.Utilitaries;

public enum StatusCode {

    NETWORKFAIL(-1),
    CREATED(201),
    BADREQUEST (400),
    UNAUTHORIZED(401),
    NOTFOUND(404),
    TIMEOUT(408),
    CONFLICT(409),
    MEDIAUNSUPPORTED (415),
    SERVEURERROR(500);


    private Integer errorCode;

    //Constructeur
    StatusCode(Integer errorCode){
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String toString() {
        return String.valueOf(errorCode);
    }
}
