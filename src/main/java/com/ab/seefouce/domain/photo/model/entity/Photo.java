package com.ab.seefouce.domain.photo.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.util.List;

/**
 * @author cattleyuan
 * @date 2024/5/26
 */
@Data
@Document("photo")
@Accessors(chain = true)
public class Photo {
    @Id
    String id;

    Long userId;
    //蜕变前
    String beforeChange;
    //蜕变后
    String afterChange;
    //健身日常
    List<String> fitnessRoutine;
}
