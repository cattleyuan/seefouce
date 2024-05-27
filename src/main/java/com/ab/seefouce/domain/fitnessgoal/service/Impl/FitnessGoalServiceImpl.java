package com.ab.seefouce.domain.fitnessgoal.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.ab.seefouce.common.base.UserContextHolder;
import com.ab.seefouce.common.base.UserHelper;
import com.ab.seefouce.common.constants.MongoConstant;
import com.ab.seefouce.domain.fitnessgoal.model.dto.FitnessGoalDTO;
import com.ab.seefouce.domain.fitnessgoal.model.entity.FitnessGoal;
import com.ab.seefouce.domain.fitnessgoal.model.vo.FitnessGoalVO;
import com.ab.seefouce.domain.fitnessgoal.resposity.FitnessGoalResposity;
import com.ab.seefouce.domain.fitnessgoal.service.FitnessGoalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * @author cattleyuan
 * @date 2024/5/24
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class FitnessGoalServiceImpl implements FitnessGoalService {

    private final FitnessGoalResposity fitnessGoalResposity;
    private final MongoTemplate mongoTemplate;

    @Override
    public void saveOrUpdateFitnessGoal(FitnessGoalDTO fitnessGoalDTO) {

        FitnessGoal fitnessGoal = buildFitnessGoal(fitnessGoalDTO);
        fitnessGoalResposity.save(fitnessGoal);
    }

    @Override
    public FitnessGoalVO getFitnessGoal() {
        FitnessGoalVO fitnessGoalVO;
        FitnessGoal fitnessGoal;
        //查询实体类FitnessGoal
        fitnessGoal = buildFitnessGoalInQuery();
        //封装返回对象
        fitnessGoalVO = new FitnessGoalVO();
        BeanUtil.copyProperties(fitnessGoal, fitnessGoalVO);

        return fitnessGoalVO;
    }

    private FitnessGoal buildFitnessGoalInQuery() {

        //获取当前线程用户信息
        UserHelper userHelper = UserContextHolder.get();
        Long userId = userHelper.getUserId();

        //构建查询条件
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));

        FitnessGoal fitnessGoal = mongoTemplate.findOne(query, FitnessGoal.class, MongoConstant.FITNESS_GOAL_CACHE_NAME);

        return fitnessGoal;
    }

    private FitnessGoal buildFitnessGoal(FitnessGoalDTO fitnessGoalDTO) {
        FitnessGoal fitnessGoal;

        UserHelper userHelper = UserContextHolder.get();
        Long userId = userHelper.getUserId();

        //构建实体查询条件
        fitnessGoal = new FitnessGoal();
        fitnessGoal.setUserId(userId);

        //若查到证明是更新操作,设置id
        fitnessGoalResposity.findOne(Example.of(fitnessGoal)).ifPresent(data -> {
            fitnessGoal.setId(data.getId());
        });

        //赋值实体类
        BeanUtil.copyProperties(fitnessGoalDTO, fitnessGoal, CopyOptions.create().setIgnoreNullValue(true));
        fitnessGoal.setUserId(userId);


        return fitnessGoal;
    }
}
