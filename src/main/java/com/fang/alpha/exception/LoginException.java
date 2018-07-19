package com.fang.alpha.exception;

public class LoginException extends RuntimeException {
    private int errorCode = -10;
    private boolean propertiesKey = true;

    public static final int NoSuchUser = -10;
    public static final int UnameOrPassWrong = -11;
    public static final int NotLogin = -12;
    public static final int TokenWrong = -13;
    public static final int WrongRole = -14;
    public static final int FailedToChPass = -15;

    public LoginException(String message){
        super(message);
    }

    public LoginException(int errorCode,String message){
        this(errorCode,message,true);
    }

    public LoginException(int errorCode,String message,Throwable cause){
        this(errorCode,message,cause,true);
    }

    public LoginException(int errorCode,String message,boolean propertiesKey){
        super(message);
        this.setErrorCode(errorCode);
        this.setPropertiesKey(propertiesKey);
    }

    public LoginException(int errorCode,String message, Throwable cause,boolean propertiesKey){
        super(message,cause);
        this.setErrorCode(errorCode);
        this.setPropertiesKey(propertiesKey);
    }

    public LoginException(String message,Throwable cause){
        super(message,cause);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isPropertiesKey() {
        return propertiesKey;
    }

    public void setPropertiesKey(boolean propertiesKey) {
        this.propertiesKey = propertiesKey;
    }
}
