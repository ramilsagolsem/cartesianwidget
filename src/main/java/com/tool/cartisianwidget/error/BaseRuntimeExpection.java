package com.tool.cartisianwidget.error;

public class BaseRuntimeExpection extends RuntimeException {

    private String errorText;
    private Long errorCode;
    private String debugMessage;

    public BaseRuntimeExpection(String message, Long errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.errorText = message;
    }

    public BaseRuntimeExpection(String message, Long errorCode, String debugMessage) {
        super(message);
        this.errorCode = errorCode;
        this.errorText = message;
        this.debugMessage = debugMessage;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public Long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Long errorCode) {
        this.errorCode = errorCode;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }
}
