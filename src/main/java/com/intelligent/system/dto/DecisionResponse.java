package com.intelligent.system.dto;

import java.time.Instant;

public record DecisionResponse(
        Long id,
        String title,
        String recommendation,
        String rationale,
        double confidence,
        Instant createdAt
        ) {}
