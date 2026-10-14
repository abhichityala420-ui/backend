package com.intelligent.system.service.impl;

import com.intelligent.system.dto.RuleDTO;
import com.intelligent.system.entity.AutomationRule;
import com.intelligent.system.exception.ResourceNotFoundException;
import com.intelligent.system.repository.AutomationRuleRepository;
import com.intelligent.system.service.AutomationService;
import com.intelligent.system.service.rules.RuleEngineService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutomationServiceImpl implements AutomationService {

    private final AutomationRuleRepository repository;
    private final RuleEngineService ruleEngineService;

    public AutomationServiceImpl(AutomationRuleRepository repository, RuleEngineService ruleEngineService) {
        this.repository = repository;
        this.ruleEngineService = ruleEngineService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RuleDTO> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    @Transactional
    public RuleDTO create(RuleDTO dto) {
        AutomationRule saved = repository.save(new AutomationRule(dto.name(), dto.description(),
                dto.condition(), dto.action(), dto.active()));
        return toDto(saved);
    }

    @Override
    @Transactional
    public RuleDTO update(Long id, RuleDTO dto) {
        AutomationRule rule = getRule(id);
        rule.setName(dto.name());
        rule.setDescription(dto.description());
        rule.setConditionExpression(dto.condition());
        rule.setAction(dto.action());
        rule.setActive(dto.active());
        return toDto(repository.save(rule));
    }

    @Override
    @Transactional
    public RuleDTO toggle(Long id, boolean active) {
        AutomationRule rule = ruleEngineService.setEnabled(getRule(id), active);
        return toDto(repository.save(rule));
    }

    @Override
    public void delete(Long id) {
        repository.delete(getRule(id));
    }

    private AutomationRule getRule(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rule", id));
    }

    private RuleDTO toDto(AutomationRule rule) {
        return new RuleDTO(rule.getId(), rule.getName(), rule.getDescription(), rule.getConditionExpression(),
                rule.getAction(), rule.isActive(), rule.getRuns(), rule.getLastRun());
    }
}
