package com.intelligent.system.service;

import com.intelligent.system.dto.RuleDTO;
import java.util.List;

public interface AutomationService {

    List<RuleDTO> findAll();

    RuleDTO create(RuleDTO rule);

    RuleDTO update(Long id, RuleDTO rule);

    RuleDTO toggle(Long id, boolean active);

    void delete(Long id);
}
