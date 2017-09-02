package io.tchepannou.academy.controller;

import io.tchepannou.academy.dto.BaseResponse;
import io.tchepannou.academy.service.TransactionIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public abstract class BaseController {
    @Autowired
    private TransactionIdGenerator transactionIdGenerator;

    protected <T extends BaseResponse> T init(T response){
        response.setTransactionId(transactionIdGenerator.generate());
        response.setTransactionDateTime(new Date());
        return response;
    }
}
