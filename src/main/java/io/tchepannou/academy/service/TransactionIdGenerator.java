package io.tchepannou.academy.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TransactionIdGenerator {
    private String id = UUID.randomUUID().toString();

    public String generate(){
        return id;
    }
}
