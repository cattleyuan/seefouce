package com.ab.seefouce.domain.fitgoal.service;

import com.ab.seefouce.domain.fitgoal.model.dto.FitnessGoalDTO;
import com.ab.seefouce.domain.fitgoal.model.dto.UpdateFitnessGoalDTO;
import com.ab.seefouce.domain.fitgoal.model.entity.FitnessGoal;

/**
 * @author cattleyuan
 * @date 2024/5/24
 */
public interface FitnessGoalService {
    void saveFitnessGoal(FitnessGoalDTO fitnessGoalDTO);

    FitnessGoal getFitnessGoal();


}
