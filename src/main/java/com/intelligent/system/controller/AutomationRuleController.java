package com.intelligent.system.controller;

import com.intelligent.system.dto.RuleDTO;
import com.intelligent.system.service.AutomationService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rules")
public class AutomationRuleController {
    private final AutomationService automationService;

    public AutomationRuleController(AutomationService automationService) {
        this.automationService = automationService;
    }

    @GetMapping
    public List<RuleDTO> findAll() { return automationService.findAll(); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RuleDTO create(@Valid @RequestBody RuleDTO rule) { return automationService.create(rule); }

    @PutMapping("/{id}")
    public RuleDTO update(@PathVariable Long id, @Valid @RequestBody RuleDTO rule) {
        return automationService.update(id, rule);
    }

    @PatchMapping("/{id}/active")
    public RuleDTO toggle(@PathVariable Long id, @RequestBody Map<String, Boolean> body) {
        return automationService.toggle(id, Boolean.TRUE.equals(body.get("active")));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { automationService.delete(id); }
}
