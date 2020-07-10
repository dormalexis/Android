package com.example.smartcity.Utilitaries;

import com.example.smartcity.R;

public enum  StatusCode {

    NETWORKFAIL(0),
    OK(200),
    CREATED(201),
    NOCONTENT(204),
    BADREQUEST(400),
    UNAUTHORIZED(401),
    NOTALLOWED(403),
    NOTFOUND(404),
    CONFLICT(409),

    INTERNALSERVERERROR(500),
    TIMEOUT(504);


    private int errorcode;

    StatusCode(int errorcode)
    {
        this.errorcode = errorcode;
    }

    public int getMessage() {
        return errorcode;
    }
}
