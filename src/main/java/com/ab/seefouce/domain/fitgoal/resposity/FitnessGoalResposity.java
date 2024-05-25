package com.ab.seefouce.domain.fitgoal.resposity;

import com.ab.seefouce.domain.fitgoal.model.entity.FitnessGoal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author cattleyuan
 * @date 2024/5/25
 */
@Repository
public interface FitnessGoalResposity extends MongoRepository<FitnessGoal,String> {
}
