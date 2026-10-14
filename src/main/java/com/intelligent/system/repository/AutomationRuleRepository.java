package com.intelligent.system.repository;

import com.intelligent.system.entity.AutomationRule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutomationRuleRepository extends JpaRepository<AutomationRule, Long> {

    long countByActiveTrue();
}
