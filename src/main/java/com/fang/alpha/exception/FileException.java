package com.fang.alpha.exception;

public class FileException extends RuntimeException {
    private int errorCode = -10;
    private boolean propertiesKey = true;

    public static final int UploadFailed = -30;
    public static final int UpdateFailed = -31;
    public static final int DeleteFailed = -32;
    public static final int WrongFileType = -33;
    public static final int NoSuchResult = -34;

    public FileException(String message){
        super(message);
    }

    public FileException(int errorCode, String message){
        this(errorCode,message,true);
    }

    public FileException(int errorCode, String message, Throwable cause){
        this(errorCode,message,cause,true);
    }

    public FileException(int errorCode, String message, boolean propertiesKey){
        super(message);
        this.setErrorCode(errorCode);
        this.setPropertiesKey(propertiesKey);
    }

    public FileException(int errorCode, String message, Throwable cause, boolean propertiesKey){
        super(message,cause);
        this.setErrorCode(errorCode);
        this.setPropertiesKey(propertiesKey);
    }

    public FileException(String message, Throwable cause){
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
