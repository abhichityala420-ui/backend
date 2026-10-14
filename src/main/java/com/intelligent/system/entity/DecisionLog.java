package com.intelligent.system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "decision_logs")
public class DecisionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String recommendation;
    private String rationale;
    private double confidence;
    private Instant createdAt;

    protected DecisionLog() {
    }

    public DecisionLog(String title, String recommendation, String rationale, double confidence) {
        this.title = title;
        this.recommendation = recommendation;
        this.rationale = rationale;
        this.confidence = confidence;
    }

    @PrePersist
    void onCreate() {
        createdAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public String getRationale() {
        return rationale;
    }

    public double getConfidence() {
        return confidence;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
