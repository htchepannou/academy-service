package io.tchepannou.academy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "T_VIDEO_TYPE")
public class VideoType extends PersistentEnum {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="url_regexp")
    private String urlRegexp;

    @Column(name="video_id_index")
    private Integer videoIdIndex;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getUrlRegexp() {
        return urlRegexp;
    }

    public void setUrlRegexp(final String urlRegexp) {
        this.urlRegexp = urlRegexp;
    }

    public Integer getVideoIdIndex() {
        return videoIdIndex;
    }

    public void setVideoIdIndex(final Integer videoIdIndex) {
        this.videoIdIndex = videoIdIndex;
    }
}
