package com.intelligent.system.service;

import com.intelligent.system.dto.DecisionRequest;
import com.intelligent.system.dto.DecisionResponse;
import java.util.List;

public interface DecisionService {

    DecisionResponse create(DecisionRequest request);

    List<DecisionResponse> findAll();
}
