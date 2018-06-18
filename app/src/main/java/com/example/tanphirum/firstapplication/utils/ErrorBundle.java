package com.example.tanphirum.firstapplication.utils;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * Created by phirum on 11/9/15.
 */
public class ErrorBundle {

    private final String appMessage;
    private final String rawMessage;

    public ErrorBundle(String message, String reason) {
        this.appMessage = message;
        this.rawMessage = reason;
    }

    public String getAppMessage(){
        return appMessage;
    }

    public String getRawMessage(){
        return rawMessage;
    }

    public static ErrorBundle adapt(Throwable throwable){
        if(throwable.getCause() instanceof UnknownHostException){
            String reason = null;
            if(throwable.getCause() != null){
                //reason = throwable.getCause().getMessage();
                reason ="Please verify your internet connection.";
            }
            if(reason == null){
                reason ="Please verify your internet connection.";
            }
            return new ErrorBundle("No Connection.", reason);
        }
        if(throwable.getCause() instanceof SocketTimeoutException){
            String reason = null;
            if(throwable.getCause() != null){
                reason = throwable.getCause().getMessage();
            }
            if(reason == null){
                reason = "Connection timed out";
            }
            return new ErrorBundle("Socket timout", reason);
        }
        if(throwable.getCause() instanceof InternalError){
            String reason = null;
            if(throwable.getCause() != null){
                reason = throwable.getCause().getMessage();
            }
            if(reason == null){
                reason = "internal error.";
            }
            return new ErrorBundle("Internal error",reason);
        }

        return new ErrorBundle("Unknown exception", throwable.getMessage());
    }
}
