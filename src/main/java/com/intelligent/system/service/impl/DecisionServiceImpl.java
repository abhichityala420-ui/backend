package com.intelligent.system.service.impl;

import com.intelligent.system.dto.DecisionRequest;
import com.intelligent.system.dto.DecisionResponse;
import com.intelligent.system.entity.DecisionLog;
import com.intelligent.system.repository.DecisionLogRepository;
import com.intelligent.system.service.DecisionService;
import com.intelligent.system.service.ai.AiDecisionService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DecisionServiceImpl implements DecisionService {

    private final DecisionLogRepository decisionLogRepository;
    private final AiDecisionService aiDecisionService;

    public DecisionServiceImpl(DecisionLogRepository decisionLogRepository, AiDecisionService aiDecisionService) {
        this.decisionLogRepository = decisionLogRepository;
        this.aiDecisionService = aiDecisionService;
    }

    @Override
    @Transactional
    public DecisionResponse create(DecisionRequest request) {
        String recommendation = aiDecisionService.recommend(request.title(), request.context());
        double confidence = aiDecisionService.estimateConfidence(request.context());
        DecisionLog saved = decisionLogRepository.save(new DecisionLog(
                request.title(), recommendation, aiDecisionService.explain(recommendation), confidence));
        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DecisionResponse> findAll() {
        return decisionLogRepository.findAll().stream().map(this::toResponse).toList();
    }

    private DecisionResponse toResponse(DecisionLog log) {
        return new DecisionResponse(log.getId(), log.getTitle(), log.getRecommendation(),
                log.getRationale(), log.getConfidence(), log.getCreatedAt());
    }
}
