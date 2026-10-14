package com.intelligent.system.controller;

import com.intelligent.system.dto.DecisionRequest;
import com.intelligent.system.dto.DecisionResponse;
import com.intelligent.system.service.DecisionService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/decisions")
public class DecisionController {
    private final DecisionService decisionService;

    public DecisionController(DecisionService decisionService) {
        this.decisionService = decisionService;
    }

    @GetMapping
    public List<DecisionResponse> findAll() {
        return decisionService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DecisionResponse create(@Valid @RequestBody DecisionRequest request) {
        return decisionService.create(request);
    }
}
