package com.fang.alpha.exception;

public class SigninException extends RuntimeException {
    private int errorCode;
    private boolean propertiesKey = true;

    public static final int CheckPassWrong = -20;
    public static final int ArgsExpected = -21;
    public static final int UserExisted = -22;

    public SigninException(String message){
        super(message);
    }

    public SigninException(int errorCode, boolean propertiesKey) {
        this.errorCode = errorCode;
        this.propertiesKey = propertiesKey;
    }

    public SigninException(int errorCode,String message){
        super(message);
        this.errorCode = errorCode;
    }

    public SigninException(String message, int errorCode, boolean propertiesKey) {
        super(message);
        this.errorCode = errorCode;
        this.propertiesKey = propertiesKey;
    }

    public SigninException(String message, Throwable cause, int errorCode, boolean propertiesKey) {
        super(message, cause);
        this.errorCode = errorCode;
        this.propertiesKey = propertiesKey;
    }

    public SigninException(Throwable cause, int errorCode, boolean propertiesKey) {
        super(cause);
        this.errorCode = errorCode;
        this.propertiesKey = propertiesKey;
    }

    public SigninException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int errorCode, boolean propertiesKey) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
        this.propertiesKey = propertiesKey;
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
