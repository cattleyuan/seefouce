package com.ab.seefouce.domain.photo.controller;

import com.ab.seefouce.common.SystemJsonResponse;
import com.ab.seefouce.domain.photo.model.dto.PhotoDTO;
import com.ab.seefouce.domain.photo.model.vo.PhotoVO;
import com.ab.seefouce.domain.photo.service.PhotoService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author cattleYuan
 * @date 2024/5/26
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/photo")
@Validated
public class PhotoController {

    private final PhotoService photoService;
    /**
     * @description: 添加或更新健身相关照片
     * @author: cattleyuan
     * @date: 2024/5/26 15:21
     * @param: photoDTO
     * @return: SystemJsonResponse
     **/
    @PostMapping("/")
    public SystemJsonResponse addOrModifyPhoto(@Validated @NotNull(message = "请求参数不能为空") @RequestBody PhotoDTO photoDTO){
        photoService.saveOrUpdatePhoto(photoDTO);
        return SystemJsonResponse.SYSTEM_SUCCESS();
    }


}
