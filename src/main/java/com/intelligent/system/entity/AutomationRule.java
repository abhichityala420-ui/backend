package com.intelligent.system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "automation_rules")
public class AutomationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String conditionExpression;
    private String action;
    private boolean active;
    private long runs;
    private Instant createdAt;
    private Instant lastRun;

    protected AutomationRule() {
    }

    public AutomationRule(String name, String description, String conditionExpression, String action, boolean active) {
        this.name = name;
        this.description = description;
        this.conditionExpression = conditionExpression;
        this.action = action;
        this.active = active;
    }

    @PrePersist
    void onCreate() {
        createdAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getConditionExpression() {
        return conditionExpression;
    }

    public String getAction() {
        return action;
    }

    public boolean isActive() {
        return active;
    }

    public long getRuns() {
        return runs;
    }

    public Instant getLastRun() {
        return lastRun;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setConditionExpression(String conditionExpression) {
        this.conditionExpression = conditionExpression;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setRuns(long runs) {
        this.runs = runs;
    }

    public void setLastRun(Instant lastRun) {
        this.lastRun = lastRun;
    }
}
