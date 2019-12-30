package com.example.smartcity.Utilitaries;

import com.example.smartcity.R;

public enum  ApiResponseErrorCode {
    BADREQUEST (R.string.BADREQUEST),
    UNAUTHORIZED(R.string.UNAUTHORIZED),
    NOTFOUND(R.string.NOTFOUND),
    NOTALLOWED(R.string.NOTALLOWED),
    TIMEOUT(R.string.TIMEOUT),
    SERVEURERROR(R.string.SERVEURERROR),
    NETWORKFAIL(R.string.NETWORKFAIL),
    OTHERERROR(R.string.OTHERERROR);

    private int errorcode;

    ApiResponseErrorCode(int errorcode)
    {
        this.errorcode = errorcode;
    }

    public int getMessage() {
        return errorcode;
    }
}
