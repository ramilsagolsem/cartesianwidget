package com.tool.cartisianwidget.error;

public class ObjectNotFoundException extends BaseRuntimeExpection{

    public ObjectNotFoundException(String message) {
        super(message, 1000L);
    }

    public ObjectNotFoundException(String message, String debugMessage) {
        super(message, 1000L, debugMessage);
    }
}
