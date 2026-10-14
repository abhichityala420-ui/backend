package com.intelligent.system.controller;

import com.intelligent.system.repository.AutomationRuleRepository;
import com.intelligent.system.repository.DecisionLogRepository;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final DecisionLogRepository decisions;
    private final AutomationRuleRepository rules;

    public AnalyticsController(DecisionLogRepository decisions, AutomationRuleRepository rules) {
        this.decisions = decisions;
        this.rules = rules;
    }

    @GetMapping("/overview")
    public Map<String, Object> overview() {
        Instant startOfDay = LocalDate.now(ZoneOffset.UTC).atStartOfDay().toInstant(ZoneOffset.UTC);
        var logs = decisions.findAll();
        double averageConfidence = logs.stream().mapToDouble(log -> log.getConfidence()).average().orElse(0);
        return Map.of(
                "decisionsToday", decisions.countByCreatedAtAfter(startOfDay),
                "activeRules", rules.countByActiveTrue(),
                "averageConfidence", averageConfidence,
                "totalDecisions", logs.size());
    }
}
