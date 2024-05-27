package com.ab.seefouce.domain.photo.service;

import com.ab.seefouce.domain.photo.model.dto.PhotoDTO;
import com.ab.seefouce.domain.photo.model.vo.PhotoVO;

/**
 * @author cattleyuan
 * @date 2024/5/24
 */
public interface PhotoService {

    void saveOrUpdatePhoto(PhotoDTO photoDTO);

    PhotoVO getPhoto();

}
