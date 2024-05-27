package com.ab.seefouce.domain.photo.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

/**
 * @author cattleyuan
 * @date 2024/5/26
 */
@Data
public class PhotoDTO {

    //蜕变前
    @NotNull(message = "照片不能为空")
    String beforeChange;
    //蜕变后
    @NotNull(message = "照片不能为空")
    String afterChange;
    //健身日常
    @NotNull(message = "健身日常不能为空")
    @Size(min = 0, max = 4, message = "健身照片展示最大数为4")
    List<String> fitnessRoutine;

}
