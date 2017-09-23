package io.tchepannou.academy.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.tchepannou.academy.dto.quiz.QuizResponse;
import io.tchepannou.academy.dto.quiz.QuizValidationRequest;
import io.tchepannou.academy.dto.quiz.QuizValidationResponse;
import io.tchepannou.academy.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/academy/v1/quiz", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/academy/v1/quiz", description = "Quiz")
public class QuizController extends BaseController {
    @Autowired
    private QuizService service;

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "findById", notes = "Find a quiz")
    public QuizResponse findById(@PathVariable Integer id) {
        return init(service.findById(id));
    }

    @RequestMapping(path = "/{id}/validate", method = RequestMethod.POST)
    @ApiOperation(value = "validate", notes = "Validate")
    public QuizValidationResponse validate(
            @PathVariable Integer id,
            @RequestBody @Valid QuizValidationRequest request
    ){
        return init(service.validate(id, request));
    }
}
