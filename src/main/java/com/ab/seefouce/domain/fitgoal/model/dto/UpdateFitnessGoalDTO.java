package com.ab.seefouce.domain.fitgoal.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author cattleyuan
 * @date 2024/5/24
 */
@Getter
@Setter
@Document("fitness_goal")
public class UpdateFitnessGoalDTO {
    @Id
    @NotEmpty(message = "id不能为空")
    String id;
    //健身目标
    Integer fitnessTarget;

    @Size(min =0,max = 2,message = "最大个性目标数为2")
    List<String> personalityGoals;

    @Size(min =0,max = 3,message = "最大健身爱好数为3")
    List<String> fitnessHobbies;

}
