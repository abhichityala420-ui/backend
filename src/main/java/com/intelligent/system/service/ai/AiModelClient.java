package com.intelligent.system.service.ai;

import org.springframework.stereotype.Component;

@Component
public class AiModelClient {

    public String recommend(String title, String context) {
        String normalized = (title + " " + context).toLowerCase();
        if (normalized.contains("urgent") || normalized.contains("critical")) {
            return "Escalate for immediate review";
        }
        if (normalized.contains("cost") || normalized.contains("budget")) {
            return "Run a cost-benefit review before proceeding";
        }
        return "Proceed with monitored implementation";
    }
}
