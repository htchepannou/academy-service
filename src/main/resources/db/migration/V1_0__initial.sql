-- SCHEMA
CREATE TABLE T_VIDEO_TYPE(
  id          INT         NOT NULL AUTO_INCREMENT,
  name        VARCHAR(20) NOT NULL,
  rank        INT         NOT NULL,

  url_regexp      TEXT    NOT NULL,
  video_id_index  INT,

  UNIQUE(name),
  PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE T_VIDEO(
  id                INT NOT NULL AUTO_INCREMENT,
  type_fk           INT NOT NULL REFERENCES T_VIDEO_TYPE(id),

  video_id          VARCHAR(32) NOT NULL,
  duration_second   INT,

  UNIQUE(type_fk, video_id),
  PRIMARY KEY (id)
) ENGINE = InnoDB;



CREATE TABLE T_QUIZ_TYPE(
  id          INT         NOT NULL AUTO_INCREMENT,
  name        VARCHAR(20) NOT NULL,
  rank        INT         NOT NULL,

  UNIQUE(name),
  PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE T_QUIZ(
  id         INT NOT NULL AUTO_INCREMENT,
  type_fk    INT NOT NULL REFERENCES T_QUIZ_TYPE(id),
  video_fk   INT REFERENCES T_VIDEO_TYPE(id),

  question      VARCHAR(100),
  description   TEXT,
  answer        TEXT,

  PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE T_QUIZ_CHOICE(
  id        INT NOT NULL AUTO_INCREMENT,
  quiz_fk   INT NOT NULL REFERENCES T_QUIZ(id),

  rank      INT,
  text      TEXT,
  answer    BIT,

  PRIMARY KEY (id)
) ENGINE = InnoDB;




CREATE TABLE T_COURSE_STATUS(
  id          INT         NOT NULL AUTO_INCREMENT,
  name        VARCHAR(20) NOT NULL,
  rank        INT         NOT NULL,
  published   BIT         DEFAULT false,

  UNIQUE(name),
  PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE T_COURSE_LEVEL(
  id          INT         NOT NULL AUTO_INCREMENT,
  name        VARCHAR(20) NOT NULL,
  rank        INT         NOT NULL,

  UNIQUE(name),
  PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE T_SEGMENT_TYPE(
  id          INT         NOT NULL AUTO_INCREMENT,
  name        VARCHAR(20) NOT NULL,
  rank        INT         NOT NULL,

  UNIQUE(name),
  PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE T_COURSE(
  id            INT NOT NULL AUTO_INCREMENT,
  status_fk     INT NOT NULL REFERENCES T_COURSE_STATUS(id),
  level_fk      INT NOT NULL REFERENCES T_COURSE_LEVEL(id),
  
  title               VARCHAR(100)  NOT NULL,
  summary             VARCHAR(255),
  description         TEXT,
  language            VARCHAR(2),
  published_datetime  DATETIME,
  updated_datetime    DATETIME,

  insert_timestamp    DATETIME   DEFAULT CURRENT_TIMESTAMP,
  update_timestamp    DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE T_LESSON(
  id            INT NOT NULL AUTO_INCREMENT,
  course_fk     INT NOT NULL REFERENCES T_COURSE(id),

  title               VARCHAR(100)  NOT NULL,
  rank                INT,

  insert_timestamp    DATETIME   DEFAULT CURRENT_TIMESTAMP,
  update_timestamp    DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE T_SEGMENT(
  id            INT NOT NULL AUTO_INCREMENT,
  lesson_fk     INT NOT NULL REFERENCES T_LESSON(id),
  type_fk       INT NOT NULL REFERENCES T_SEGMENT_TYPE(id),
  video_fk      INT     NULL REFERENCES T_VIDEO(id),
  quiz_fk       INT     NULL REFERENCES T_QUIZ(id),

  title               VARCHAR(100)  NOT NULL,
  summary             VARCHAR(255),
  rank                INT,
  description         TEXT,

  insert_timestamp    DATETIME   DEFAULT CURRENT_TIMESTAMP,
  update_timestamp    DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  PRIMARY KEY (id)
) ENGINE = InnoDB;



CREATE TABLE T_COURSE_ATTENDANCE(
  id                  INT NOT NULL AUTO_INCREMENT,
  student_fk          INT NOT NULL,
  course_fk           INT NOT NULL REFERENCES T_COURSE(id),
  current_segment_fk  INT NOT NULL REFERENCES T_SEGMENT(id),

  attendance_datetime DATETIME NOT NULL,

  PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE T_SEGMENT_ATTENDANCE(
  id                    INT NOT NULL AUTO_INCREMENT,
  course_attendance_fk  INT NOT NULL REFERENCES T_COURSE_ATTENDANCE(id),
  segment_fk            INT NOT NULL REFERENCES T_SEGMENT(id),

  attendance_datetime   DATETIME,

  UNIQUE (course_attendance_fk, segment_fk),
  PRIMARY KEY (id)
) ENGINE = InnoDB;



-- DATA
INSERT INTO T_VIDEO_TYPE(id, name, rank, url_regexp, video_id_index)  VALUES(1, 'youtube', 0, 'https://(?:www\\.)?youtu(?:\\.be/|be\\.com/(?:watch\\?v=|v/|embed/|user/(?:[\\w#]+/)+))([^&#?\n]+)', 1);

INSERT INTO T_COURSE_STATUS(id, name, rank) VALUES(1, 'draft', 0);
INSERT INTO T_COURSE_STATUS(id, name, rank, published) VALUES(2, 'published', 1, true);
INSERT INTO T_COURSE_STATUS(id, name, rank) VALUES(3, 'archived', 2);

INSERT INTO T_COURSE_LEVEL(id, name, rank) VALUES(1, 'all', 0);
INSERT INTO T_COURSE_LEVEL(id, name, rank) VALUES(2, 'beginner', 1);
INSERT INTO T_COURSE_LEVEL(id, name, rank) VALUES(3, 'intermediate', 2);
INSERT INTO T_COURSE_LEVEL(id, name, rank) VALUES(4, 'advanced', 3);

INSERT INTO T_SEGMENT_TYPE(id, name, rank) VALUES(1, 'video', 0);
INSERT INTO T_SEGMENT_TYPE(id, name, rank) VALUES(2, 'text', 1);
INSERT INTO T_SEGMENT_TYPE(id, name, rank) VALUES(3, 'quiz', 2);

INSERT INTO T_QUIZ_TYPE(id, name, rank)   VALUE(1, 'singlechoice', 0);
INSERT INTO T_QUIZ_TYPE(id, name, rank)   VALUE(2, 'multichoice', 1);
INSERT INTO T_QUIZ_TYPE(id, name, rank)   VALUE(3, 'text', 2);
