package com.ab.seefouce.domain.fitgoal.model.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;

/**
 * @author cattleyuan
 * @date 2024/5/24
 */
@Getter
@Setter
@Document("fitness_goal")
public class FitnessGoal {
    @Id
    String id;
    //用户id
    @Indexed
    Long userId;
    //健身目标
    Integer fitnessTarget;
    //个性目标
    List<String> personalityGoals;

    //健身爱好
    List<String> fitnessHobbies;

}
