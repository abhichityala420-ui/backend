package com.intelligent.system.repository;

import com.intelligent.system.entity.DecisionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DecisionLogRepository extends JpaRepository<DecisionLog, Long> {

    long countByCreatedAtAfter(java.time.Instant since);
}
