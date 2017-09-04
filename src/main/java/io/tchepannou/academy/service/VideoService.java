package io.tchepannou.academy.service;

import io.tchepannou.academy.dao.VideoDao;
import io.tchepannou.academy.dao.VideoTypeDao;
import io.tchepannou.academy.domain.Video;
import io.tchepannou.academy.domain.VideoType;
import io.tchepannou.academy.dto.video.CreateVideoRequest;
import io.tchepannou.academy.dto.video.CreateVideoResponse;
import io.tchepannou.academy.dto.video.VideoDto;
import io.tchepannou.academy.dto.video.VideoResponse;
import io.tchepannou.academy.exception.BusinessError;
import io.tchepannou.academy.exception.InvalidRequestException;
import io.tchepannou.academy.exception.NotFoundException;
import io.tchepannou.academy.mapper.VideoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class VideoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(VideoService.class);

    @Autowired
    private VideoTypeDao videoTypeDao;

    @Autowired
    private VideoDao videoDao;

    @Autowired
    private VideoMapper videoMapper;


    @Transactional
    public CreateVideoResponse create(final CreateVideoRequest request){
        final Iterable<VideoType> videoTypes = videoTypeDao.findAll();
        for (final VideoType videoType : videoTypes){
            Pattern pattern = Pattern.compile(videoType.getUrlRegexp(), Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(request.getUrl());
            if (matcher.find()) {
                final String videoId = matcher.group(videoType.getVideoIdIndex());
                Video video = create(videoId, videoType, request.getDurationSeconds());

                VideoDto dto = videoMapper.toVideoDto(video, videoType);
                return new CreateVideoResponse(dto);
            }
        }

        throw new InvalidRequestException(BusinessError.BAD_VIDEO_URL);
    }

    private Video create(final String videoId, final VideoType videoType, final Integer durationSecond){
        Video video = videoDao.findByVideoIdAndTypeId(videoId, videoType.getId());
        if (video != null){
            return video;
        }

        video = new Video();
        video.setVideoId(videoId);
        video.setTypeId(videoType.getId());
        video.setDurationSecond(durationSecond);
        videoDao.save(video);
        return video;
    }

    public VideoResponse findById (Integer id){
        final Video video = videoDao.findOne(id);
        if (video == null){
            throw new NotFoundException(BusinessError.VIDEO_NOT_FOUND);
        }

        final VideoType videoType = videoTypeDao.findOne(video.getTypeId());
        final VideoDto dto = videoMapper.toVideoDto(video, videoType);
        return new VideoResponse(dto);
    }
}
