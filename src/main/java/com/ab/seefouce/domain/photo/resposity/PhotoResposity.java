package com.ab.seefouce.domain.photo.resposity;

import com.ab.seefouce.domain.photo.model.entity.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author cattleyuan
 * @date 2024/5/26
 */
@Repository
public interface PhotoResposity extends MongoRepository<Photo,String> {
}
