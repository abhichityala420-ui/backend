package com.intelligent.system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DecisionRequest(
        @NotBlank
        @Size(max = 120)
        String title,
        @NotBlank
        @Size(max = 4000)
        String context
        ) {}
