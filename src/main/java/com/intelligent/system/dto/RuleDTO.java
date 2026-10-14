package com.intelligent.system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.Instant;

public record RuleDTO(
        Long id,
        @NotBlank
        @Size(max = 120)
        String name,
        @Size(max = 500)
        String description,
        @NotBlank
        @Size(max = 240)
        String condition,
        @NotBlank
        @Size(max = 240)
        String action,
        boolean active,
        long runs,
        Instant lastRun
        ) {}
