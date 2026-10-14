package com.intelligent.system.service.ai;

import org.springframework.stereotype.Service;

@Service
public class AiDecisionService {

    private final AiModelClient aiModelClient;

    public AiDecisionService(AiModelClient aiModelClient) {
        this.aiModelClient = aiModelClient;
    }

    public String recommend(String title, String context) {
        return aiModelClient.recommend(title, context);
    }

    public String explain(String recommendation) {
        return "Recommendation selected from the request context using the local starter model. "
                + "Review the source inputs before acting: " + recommendation + ".";
    }

    public double estimateConfidence(String context) {
        int words = context.trim().split("\\s+").length;
        return Math.min(0.94, 0.68 + (words / 100.0));
    }
}
