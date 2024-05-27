package com.ab.seefouce.domain.fitnessgoal.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @author cattleyuan
 * @date 2024/5/25
 */
@Data
public class FitnessGoalVO {
    //健身目标
     String fitnessTarget;
    //个性目标
    List<String> personalityGoals;

    //健身爱好
    List<String> fitnessHobbies;
}
