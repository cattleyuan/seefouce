package com.ab.seefouce.domain.photo.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.ab.seefouce.common.base.UserContextHolder;
import com.ab.seefouce.domain.photo.model.dto.PhotoDTO;
import com.ab.seefouce.domain.photo.model.entity.Photo;
import com.ab.seefouce.domain.photo.model.vo.PhotoVO;
import com.ab.seefouce.domain.photo.resposity.PhotoResposity;
import com.ab.seefouce.domain.photo.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * @author cattleyuan
 * @date 2024/5/24
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {
    private final PhotoResposity photoResposity;

    @Override
    public void saveOrUpdatePhoto(PhotoDTO photoDTO) {

            Long userId = UserContextHolder.get().getUserId();
            Photo photo = buildPhoto(photoDTO, userId);
            //保存或更新用户图片信息
            photoResposity.save(photo);
    }

    @Override
    public PhotoVO getPhoto() {
        Long userId = UserContextHolder.get().getUserId();
        //构建查询条件
        Photo photo = new Photo().setUserId(userId);
        //返回参数
        PhotoVO photoVO = new PhotoVO();

        photoResposity.findOne(Example.of(photo)).ifPresent(data->{
            BeanUtil.copyProperties(data,photoVO,CopyOptions.create().setIgnoreNullValue(true));
        });

        return photoVO;
    }

    //获取更新或添加的photo实体
    private Photo buildPhoto(PhotoDTO photoDTO, Long userId) {
        //根据用户id构建查询条件
        Photo photo = new Photo();
        photo.setUserId(userId);
        photoResposity.findOne(Example.of(photo)).ifPresent(data->{
            photo.setId(data.getId());
        });

        BeanUtil.copyProperties(photoDTO,photo, CopyOptions.create().setIgnoreNullValue(true));
        return photo;
    }
}
