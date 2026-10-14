package com.intelligent.system.service.rules;

import com.intelligent.system.entity.AutomationRule;
import java.time.Instant;
import org.springframework.stereotype.Service;

@Service
public class RuleEngineService {

    public AutomationRule setEnabled(AutomationRule rule, boolean enabled) {
        rule.setActive(enabled);
        return rule;
    }

    public AutomationRule recordRun(AutomationRule rule) {
        rule.setRuns(rule.getRuns() + 1);
        rule.setLastRun(Instant.now());
        return rule;
    }
}
