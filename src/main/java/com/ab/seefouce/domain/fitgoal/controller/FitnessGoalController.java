package com.ab.seefouce.domain.fitgoal.controller;

import com.ab.seefouce.common.SystemJsonResponse;
import com.ab.seefouce.domain.fitgoal.model.dto.FitnessGoalDTO;
import com.ab.seefouce.domain.fitgoal.model.dto.UpdateFitnessGoalDTO;
import com.ab.seefouce.domain.fitgoal.model.entity.FitnessGoal;
import com.ab.seefouce.domain.fitgoal.service.FitnessGoalService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author cattleYuan
 * @date 2024/5/22
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/goal")
@Validated
public class FitnessGoalController {

    private final FitnessGoalService fitnessGoalService;

    @PostMapping("/add")
    public SystemJsonResponse addFitnessGoal(@Validated @NotNull(message = "请求参数不能为空") @RequestBody FitnessGoalDTO fitnessGoalDTO){
        fitnessGoalService.saveFitnessGoal(fitnessGoalDTO);
        return SystemJsonResponse.SYSTEM_SUCCESS();
    }

    @GetMapping("/query")
    public SystemJsonResponse queryFitnessGoals(){
        FitnessGoal fitnessGoal=fitnessGoalService.getFitnessGoal();
        return SystemJsonResponse.SYSTEM_SUCCESS(fitnessGoal);
    }

}
