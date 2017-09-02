package io.tchepannou.academy.service;

import io.tchepannou.academy.dao.VideoDao;
import io.tchepannou.academy.dao.VideoTypeDao;
import io.tchepannou.academy.domain.Video;
import io.tchepannou.academy.domain.VideoType;
import io.tchepannou.academy.dto.video.VideoDto;
import io.tchepannou.academy.dto.video.VideoResponse;
import io.tchepannou.academy.exception.BusinessError;
import io.tchepannou.academy.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VideoService {
    @Autowired
    private VideoTypeDao videoTypeDao;

    @Autowired
    private VideoDao videoDao;

    @Autowired
    private VideoMapper videoMapper;


    public VideoResponse findById (Integer id){
        final Video video = videoDao.findOne(id);
        if (video == null){
            throw new NotFoundException(BusinessError.VIDEO_NOT_FOUND);
        }

        final VideoType videoType = videoTypeDao.findOne(video.getTypeId());
        final VideoDto dto = videoMapper.toVideo(video, videoType);
        return new VideoResponse(dto);
    }
}
