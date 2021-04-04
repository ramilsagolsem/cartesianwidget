package com.tool.cartisianwidget.error;

public class ValidationExpection extends BaseRuntimeExpection{

    public ValidationExpection(String message) {
        super(message, 1001L);
    }

    public ValidationExpection(String message, String debugMessage) {
        super(message, 1001L, debugMessage);
    }
}
