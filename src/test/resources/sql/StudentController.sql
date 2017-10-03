-- done
INSERT INTO T_COURSE(id, level_fk, status_fk, language, title, summary, description, published_datetime, segment_count) VALUES(300, 3, 2, 'en', 'Title of...', 'Summary of...', 'Description of...', '2017-01-02 03:30:00', 2);
INSERT INTO T_LESSON(id, course_fk, rank, title) VALUES(301, 300, 1, 'Introduction');
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, rank, title) VALUES(3011, 301, 1, 1, 'Welcome1');
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, rank, title) VALUES(3012, 301, 1, 2, 'Welcome2');

-- doneSegmentAlreadyViewed
INSERT INTO T_COURSE(id, level_fk, status_fk, language, title, summary, description, published_datetime, segment_count) VALUES(400, 3, 2, 'en', 'Title of...', 'Summary of...', 'Description of...', '2017-01-02 03:30:00', 3);
INSERT INTO T_LESSON(id, course_fk, rank, title) VALUES(401, 400, 1, 'Introduction');
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, rank, title) VALUES(4011, 401, 1, 1, 'Welcome1');
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, rank, title) VALUES(4012, 401, 1, 2, 'Welcome2');
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, rank, title) VALUES(4013, 401, 1, 3, 'Welcome3');

INSERT INTO T_STUDENT(id, role_fk, course_fk, course_segment_count) VALUES(400, 1, 400, 3);
INSERT INTO T_STUDENT_SEGMENT(id, student_fk, segment_fk) VALUES (401, 400, 4011);

-- doneAllSegments
INSERT INTO T_COURSE(id, level_fk, status_fk, language, title, summary, description, published_datetime, segment_count) VALUES(500, 3, 2, 'en', 'Title of...', 'Summary of...', 'Description of...', '2017-01-02 03:30:00', 3);
INSERT INTO T_LESSON(id, course_fk, rank, title) VALUES(501, 500, 1, 'Introduction');
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, rank, title) VALUES(5011, 501, 1, 1, 'Welcome1');
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, rank, title) VALUES(5012, 501, 1, 2, 'Welcome2');
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, rank, title) VALUES(5013, 501, 1, 3, 'Welcome3');

INSERT INTO T_STUDENT(id, role_fk, course_fk, course_segment_count) VALUES(500, 1, 500, 4);
INSERT INTO T_STUDENT_SEGMENT(id, student_fk, segment_fk) VALUES (501, 500, 5011);
INSERT INTO T_STUDENT_SEGMENT(id, student_fk, segment_fk) VALUES (502, 500, 5012);
