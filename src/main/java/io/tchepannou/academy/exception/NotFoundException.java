package io.tchepannou.academy.exception;

public class NotFoundException extends BusinessException{
    public NotFoundException(final BusinessError errorCode) {
        super(errorCode);
    }
}
