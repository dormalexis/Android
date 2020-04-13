package com.example.smartcity.Utilitaries;
public class ApiCodeTrad {


     public static ApiResponseErrorCode codeErrorToApiResponse(int errorCode){
         if(errorCode==400) {
             return ApiResponseErrorCode.BADREQUEST;
         }
         if(errorCode==401){
             return ApiResponseErrorCode.UNAUTHORIZED;
         }if(errorCode==404){
             return ApiResponseErrorCode.NOTFOUND;
         }if(errorCode==405){
             return ApiResponseErrorCode.NOTALLOWED;
         }if(errorCode==408){
             return ApiResponseErrorCode.TIMEOUT;
         }
         return ApiResponseErrorCode.OTHERERROR;
    }
}
