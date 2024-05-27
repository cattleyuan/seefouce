package com.ab.seefouce.domain.fitnessgoal.service;

import com.ab.seefouce.domain.fitnessgoal.model.dto.FitnessGoalDTO;
import com.ab.seefouce.domain.fitnessgoal.model.vo.FitnessGoalVO;

/**
 * @author cattleyuan
 * @date 2024/5/24
 */
public interface FitnessGoalService {
    void saveOrUpdateFitnessGoal(FitnessGoalDTO fitnessGoalDTO);

    FitnessGoalVO  getFitnessGoal();


}
