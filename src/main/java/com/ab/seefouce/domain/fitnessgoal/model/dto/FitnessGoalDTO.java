package com.ab.seefouce.domain.fitnessgoal.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author cattleyuan
 * @date 2024/5/24
 */
@Getter
@Setter
public class FitnessGoalDTO {

    @NotNull(message = "健身目标不能为空")
    String fitnessTarget;

    @NotNull(message = "个性目标不能为空")
    @Size(min =0,max = 2,message = "最大个性目标数为2")
    List<String> personalityGoals;

    @NotNull(message = "健身爱好不能为空")
    @Size(min =0,max = 3,message = "最大健身爱好数为3")
    List<String> fitnessHobbies;

}
