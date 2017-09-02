package io.tchepannou.academy.exception;

public class InvalidRequestException extends BusinessException{
    public InvalidRequestException(final BusinessError errorCode) {
        super(errorCode);
    }
    public InvalidRequestException(final BusinessError errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
